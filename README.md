# Axon Server Extension API

## Interceptors and Hooks

Users can extend Axon Server functionality by defining interceptors. Interceptors intercept requests that 
client applications send and perform actions before and/or after the request is handled.
Axon Server supports interceptors for the following types:
- Command
- Query (scatter/gather and point-to-point)
- Subscription Query
- Event
- Snapshot

Users can define multiple interceptors for the same point, these interceptors will be executed based on the value of 
the _order_ operation in the interceptor instance.

Each interceptor operation has an InterceptorContext instance as its first parameter. This contains information about the
caller of the request, the Axon Server context and allows implementors of interceptors to pass data in the interceptor chain. 

### Command

Commands can be intercepted before they are sent to a command handler and Command Results are intercepted after the 
command handler has handled the command.

Axon Server executes all registered _CommandRequestInterceptor_ instances before sending the command to the handler.
If one of the interceptor instances throws an exception Axon Server will not send the command to the handler, and it will
return a CommandResponse with an error to the client that sent the command.

Once the command handler has handled the command and returned the reply to Axon Server, Axon Server executes all registered
_CommandResponseInterceptor_ instances. The response interceptor will only receive the CommandResponse object and the 
InterceptorContext, if it needs any information of the Command request, this has to be added to the InterceptorContext by a
CommandRequestInterceptor. The response interceptor is also executed when the command failed in the command handler. 
In this case the CommandResponse object contains an error code and error message.

### Query

Similar interceptors to commands (QueryRequestInterceptor and QueryResponseInterceptor).
Note that a query may return multiple responses, in case of a scatter/gather query. In this case Axon Server executes
the response interceptors for each response.

### Subscription Query

### Event

For events there are interceptors around the storing of events and interceptors for reading events. 

_Storing events_

When storing events, a client sends a stream of events to Axon Server, for each event that reaches Axon Server, Axon Server
executes the _AppendEventInterceptor_ instances. These interceptors can manipulate the content of the event, and if one of the 
interceptors throws an error the transaction fails. 

When the client closes the stream, to commit the events, Axon Server executes the _EventsPreCommitInterceptor_ instances.
This interceptor only receives the InterceptorContext, it does not get the events that were part of the transaction. 
If the interceptor needs information about the events in the transaction, an AppendEventInterceptor should add the information
to the context. 

Once Axon Server has stored the events in the event store, and before it returns the confirmation to the client, it
executes any _EventsPostCommitInterceptor_ instances. Just like the EventsPreCommitInterceptor this interceptor does not
have access to the events.

_Reading events_

Axon Server executes the _EventReadInterceptor_ instances for each event read from the event store and sent to a client 
application. The interceptor may change the content of the event.

### Snapshot

For snapshots there are interceptors around the storing of snapshot and interceptors for reading snapshots.
Before storing a snapshot, Axon Server executes all _SnapshotPreCommitInterceptor_ instances.
**Note: there is no SnapshotPostCommitInterceptor yet.**

On sending snapshots to a client, Axon Server executes all _SnapshotReadInterceptor_ instances.  

### Event transformers



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

    private Set<ServiceRegistration> registration = new HashSet<>();

    public void start(BundleContext bundleContext) {
        Dictionary dictionary = new Hashtable();
        registration.add(bundleContext.registerService(ReadEventInterceptor.class.getName(),
                                                       new FirstEventReadInterceptor(),
                                                       dictionary));
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
            <groupId>org.apache.felix</groupId>
            <artifactId>org.osgi.core</artifactId>
            <version>1.0.0</version>
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
                        <Import-Package>io.axoniq.axonserver.extensions.*;io.axoniq.axonserver.grpc.*;org.osgi.framework</Import-Package>
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

