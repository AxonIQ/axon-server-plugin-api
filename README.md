# Axon Server Extension API

## Interceptors and Hooks

Users can extend Axon Server functionality by defining interceptors and hooks. 
Interceptors intercept requests that client applications send and perform actions before and/or after the request is handled.
Hooks can perform actions on messages, but are not able to change the content of the messages.

Axon Server supports interceptors/hooks for the following types:
- Command
- Query (scatter/gather and point-to-point)
- Subscription Query
- Event
- Snapshot

Users can define multiple interceptors for the same point, these interceptors will be executed based on the value of 
the _order_ operation in the interceptor instance.

Each interceptor operation has an _ExtensionUnitOfWork_ instance as its last parameter. This contains information about the
caller of the request, the Axon Server context and allows implementors of interceptors to pass data in the interceptor chain. 
For instance, for a command the _ExtensionUnitOfWork_ for the request interceptors is the same instance as the one provided
in the response interceptors.

### Command

Commands can be intercepted before they are sent to a command handler and Command Results are intercepted after the 
command handler has handled the command.

Axon Server executes all registered _CommandRequestInterceptor_ instances before sending the command to the handler.
If one of the interceptor instances throws an exception Axon Server will not send the command to the handler, and it will
return a CommandResponse with an error to the client that sent the command.

Once the command handler has handled the command and returned the reply to Axon Server, Axon Server executes all registered
_CommandResponseInterceptor_ instances. The response interceptor will only receive the CommandResponse object and the
_ExtensionUnitOfWork_, if it needs any information of the Command request, this has to be added to the _ExtensionUnitOfWork_ by a
CommandRequestInterceptor. The response interceptor is also executed when the command failed in the command handler. 
In this case the CommandResponse object contains an error code and error message.

### Query

Similar interceptors to commands (QueryRequestInterceptor and QueryResponseInterceptor).
Note that a query may return multiple responses, in case of a scatter/gather query. In this case Axon Server executes
the response interceptors for each response.

### Subscription Query

The subscription query interceptors intercept SubscriptionQueryRequest and SubscriptionQueryResponse messages. These messages
are complex messages that contain different types of messages. 

Each SubscriptionQueryRequest contains one of the following types:
- Subscribe, to subscribe to the updates for the subscription query
- Unsubscribe, to unsubscribe from updates
- GetInitialResult, to request the initial result
- FlowControl, to grant permits to the updates providers to send more updates

A SubscriptionQueryResponse message contains one of the following types:
- InitialResult, the initial result for the subscription query 
- Update, an update for the subscription query
- Complete, an indicator that the query is complete and no more updates will be sent  
- CompleteExceptionally, an indicator that the query is completed exceptionally and no more updates will be sent  

### Event

For events there are interceptors around the storing of events and interceptors for reading events. 

_Storing events_

When storing events, a client sends a stream of events to Axon Server, for each event that reaches Axon Server, Axon Server
executes the _AppendEventInterceptor_ instances. These interceptors can manipulate the content of the event, and if one of the 
interceptors throws an error the transaction fails. 

When the client closes the stream, to commit the events, Axon Server executes the _EventsPreCommitHook_ instances.
This interceptor receives the list of events in the transaction and the _ExtensionUnitOfWork_. 

Once Axon Server has stored the events in the event store, and before it returns the confirmation to the client, it
executes any _EventsPostCommitHook_ instances. 

If the one of the _AppendEventInterceptor_ or _EventsPreCommitHook_ makes changes in an external system, that you want to 
have undone if the transaction was cancelled, the interceptor can register an _onFailure_ action in the _ExtensionUnitOfWork_.
If the transaction fails for any reason in Axon Server, or because one of the subsequent interceptors throws an exception, all the
registered _onFailure_ actions are executed. The actions are executed in reverse order (the last registered action is executed first).

_Reading events_

Axon Server executes the _EventReadInterceptor_ instances for each event read from the event store and sent to a client 
application. The interceptor may change the content of the event.

### Snapshot

For snapshots there are interceptors around the storing of snapshot and interceptors for reading snapshots.
Before storing a snapshot, Axon Server executes all _AppendSnapshotInterceptor_ instances. Once the snapshot is stored
in AxonServer, it executes all _PostCommitSnapshotHook_ instances.

Similar as for events, you can also register _onFailure_ actions from interceptors from the _AppendSnapshotInterceptor_ 
interceptors.

On sending snapshots to a client, Axon Server executes all _SnapshotReadInterceptor_ instances.  

### Building extensions

To add extensions in Axon Server, create an OSGi module that contains the extension implementations. You can add 
all extensions in a single module, or you can define multiple modules, each containing some interceptors.

The implementation of the OSGi module must contain an _BundleActivator_ class, that makes the interceptors available in the OSGi 
container. 

```java
package org.example.interceptor;

import io.axoniq.axonserver.extensions.interceptor.ReadEventInterceptor;
import org.example.interceptor.impl.FirstEventReadInterceptor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import java.util.Dictionary;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

public class Activator implements BundleActivator {

    private Set<ServiceRegistration<?>> registration = new HashSet<>();

    public void start(BundleContext bundleContext) {
        registration.add(bundleContext.registerService(ReadEventInterceptor.class,
                                                       new FirstEventReadInterceptor(),
                                                       null));
    }

    public void stop(BundleContext bundleContext) {
        registration.forEach(ServiceRegistration::unregister);
    }

}
```

To bundle the interceptors in a jar file you can use the following maven template:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <groupId>org.sample</groupId>
    <version>1.0.1-SNAPSHOT</version>
    <artifactId>custom-interceptors</artifactId>
    <modelVersion>4.0.0</modelVersion>

    <!-- Set to bundle to create an OSGi bundle jar file -->
    <packaging>bundle</packaging>

    <dependencies>
        <!-- Dependency on the axonserver-extenstion-api as this contains all the extension interfaces, 
             provided as it should not be included in the output bundle -->
        <dependency>
            <groupId>io.axoniq</groupId>
            <artifactId>axonserver-extension-api</artifactId>
			<version>4.5-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
        <!-- Access to the OSGI classes, 
             provided as it should not be included in the output bundle -->
        <dependency>
            <groupId>org.osgi</groupId>
            <artifactId>org.osgi.core</artifactId>
            <version>6.0.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>   
                <groupId>org.apache.felix</groupId>
                <artifactId>maven-bundle-plugin</artifactId>
                <extensions>true</extensions>
                <configuration>
                    <instructions>
                        <Embed-Dependency>*;scope=compile|runtime</Embed-Dependency>
                        <!-- import the packages exposed by the extension api and the Axon Server message types --> 
                        <Import-Package>io.axoniq.axonserver.extensions.*;io.axoniq.axonserver.grpc.*;org.osgi.framework;com.google.protobuf.*</Import-Package>
                        <!-- internal packages for the bundle --> 
                        <Private-Package>org.example.interceptor.*</Private-Package>
                        <!-- references the BundleActivator class -->
                        <Bundle-Activator>org.example.interceptor.Activator</Bundle-Activator>
                    </instructions>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

You can change the contents of a request inside an interceptor. As the request passed to the interceptor is immutable,
you need to create a new version of the request. The recommended way is to initialize a builder with the current request
and set the changed values in the builder. The following example adds a meta-data field in an event:

```
@Override
public Event appendEvent(Event event,ExtensionUnitOfWork extensionContext) {
  return Event.newBuilder(event)
              .putMetaData("createdBy",
                  MetaDataValue.newBuilder()
                               .setTextValue(extensionContext.principal()==null?
                                    "[anonymous]":
                                     extensionContext.principal())
                               .build())
              .build();
        }
```
Extensions can require configuration you don't want to hard-code in the package. 
To define these configurable properties, you can implement a class implementing the ConfigurationListener interface, 
define bind this to the bundle context and pass the class to the interceptor. 
The configuration is set in Axon Server per context. 

Here's an example of a configuration listener class:
```java
package org.sample.impl;

import io.axoniq.axonserver.extensions.AttributeType;
import io.axoniq.axonserver.extensions.Cardinality;
import io.axoniq.axonserver.extensions.Configuration;
import io.axoniq.axonserver.extensions.ConfigurationListener;
import io.axoniq.axonserver.extensions.ExtensionPropertyDefinition;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Arrays.asList;

public class SampleConfigurator implements ConfigurationListener {

    private final List<ExtensionPropertyDefinition> extensionPropertyDefinitions = new LinkedList<>();
    private final ConcurrentHashMap<String, Map<String, ?>> configurationPerContext = new ConcurrentHashMap<>();

    public SampleConfigurator() {
        extensionPropertyDefinitions.add(ExtensionPropertyDefinition.newBuilder("hostname", "Hostname")
                                                                    .defaultValue("127.0.0.1")
                                                                    .description("The hostname")
                                                                    .build());
        extensionPropertyDefinitions.add(ExtensionPropertyDefinition.newBuilder("username", "Username")
                                                                    .defaultValue("guest")
                                                                    .build());
        extensionPropertyDefinitions.add(ExtensionPropertyDefinition.newBuilder("password", "Password")
                                                                    .type(AttributeType.PASSWORD)
                                                                    .build());
    }

    @Override
    public void updated(String context, Map<String, ?> configuration) {
        if (configuration == null) {
            configurationPerContext.remove(context);
        } else {
            configurationPerContext.put(context, configuration);
        }
    }


    @Override
    public Configuration configuration() {
        return new Configuration(extensionPropertyDefinitions, "demo");
    }

    public Object get(String context, String property) {
        return configurationPerContext.getOrDefault(context, Collections.emptyMap()).get(property);
    }
}
```

The constructor sets up a list of configurable properties. For each property you can define the type, a default value, 
the cardinality and a list of options. Axon Server uses the configuration operation to retrieve the information about the 
configurable properties. When an extension is started for a context, or when the properties are updated through Axon Server,
Axon Server calls the updated operation. 

The following example shows how the configuration listener is registered in the bundle context and passed to an interceptor:
```java
package org.sample;

import io.axoniq.axonserver.extensions.ConfigurationListener;
import io.axoniq.axonserver.extensions.interceptor.AppendEventInterceptor;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.sample.impl.EventInterceptor;
import org.sample.impl.SampleConfigurator;

import java.util.ArrayList;
import java.util.List;

public class ConfigActivator implements BundleActivator {

    private final List<ServiceRegistration<?>> registrations = new ArrayList<>();

    @Override
    public void start(BundleContext context)  {
        SampleConfigurator configurationListener = new SampleConfigurator();

        registrations.add(context.registerService(ConfigurationListener.class, configurationListener, null));
        registrations.add(context.registerService(AppendEventInterceptor.class, 
                                new EventInterceptor(configurationListener),
                                null));
    }

    @Override
    public void stop(BundleContext context)  {
        registrations.forEach(ServiceRegistration::unregister);
    }
}
```