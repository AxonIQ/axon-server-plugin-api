# Common Xcode Error Codes

## Build Errors

- **Clang Error Codes**
  - `Clang-1`: Compile-time error (general)
  - `Clang-2`: Link-time error
  - `Clang-3`: Parse error
  - `Clang-4`: Semantic error
  - `Clang-5`: Warning

- **Swift Error Codes**
  - `Swift Compiler Error`: General Swift compilation error
  - `Swift Compiler Warning`: Swift compilation warning

## Runtime Errors

- `EXC_BAD_ACCESS (SIGSEGV)`: Memory access violation
- `EXC_BAD_INSTRUCTION (EXC_I386_INVOP)`: Illegal instruction
- `EXC_ARITHMETIC (EXC_I386_DIV)`: Arithmetic exception (e.g., division by zero)
- `EXC_BAD_SYSCALL`: Invalid system call
- `EXC_CRASH`: Application crash
- `EXC_RESOURCE`: Resource limit exceeded

## Signing and Provisioning Errors

- `Code Sign error`: Issues with code signing
- `No provisioning profiles found`: Missing or invalid provisioning profile
- `No signing certificate "iOS Development" found`: Missing or invalid signing certificate

## Interface Builder Errors

- `IBDesignableAgent crashed`: Error in Interface Builder live rendering
- `Failed to render and update auto layout status`: Auto layout issues in Interface Builder

## Dependency and Package Manager Errors

- `Unable to resolve package dependencies`: CocoaPods or Swift Package Manager dependency resolution failure
- `Command PhaseScriptExecution failed`: Error in a build phase script

## Device and Simulator Errors

- `This iPhone is running iOS X.X, which may not be supported by this version of Xcode`: Xcode version incompatible with device iOS version
- `Unable to launch X`: General launch failure for app or test

## Testing Errors

- `Test failed`: General test failure
- `Test didn't run to completion`: Test execution interrupted or timed out

## Archiving and Distribution Errors

- `No accounts with iTunes Connect access have been found`: Account access issues for app distribution
- `ITMS-90000`: General App Store Connect submission error

Remember that these error codes are often accompanied by more detailed error messages that can provide additional context about the specific issue.
