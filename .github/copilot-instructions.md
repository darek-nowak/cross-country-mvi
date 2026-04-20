# Copilot Instructions

## Project Scope
Project CrossCountry app is using latest clean Android architecture guidelines,
version catalogue for lib and plugins dependencies


## Architecture Rules
Use MVI architecture with clearly defined layers and responsibilities:
- UI Screens
- ViewModel
- Domain UseCases
- Repository pattern
- Retrofit Remote Source

## Testing Rules
Cover testing using the same layers and tools documented in the project:

### Unit Tests
- JUnit5 with mockk library
- Extension classes for mocking coroutine Dispatchers
- CoroutineDispatchersProvider injected to classes
- google Truth assertions
- cashapp Turbine for Flow testing

### Screenshots Testing
- paparazzi lib for testing UI on jvm

### UI interactive testing
- action & verification robots


