# CrossCountry application for demonstratimg clean android architecture with tests

## MVI Architecture with clearly defined layers and their responsibilities:
- UI Screens
- ViewModel
- Domain UseCases
- Repository pattern
- Retrofit Remote Source

## Testing layers

### Unit Tests
- JUnit5 with mockk library 
- Extension classes for mocking coroutine Dispatchers
- CoroutineDispatchersProvider injected to classes
- google Truth assertions
- cashapp Turbine for Flow testing
- Extensions 

### Screenshots Testing
- paparazzi lib for testing UI on jvm

### UI interactive testing
- action & verification robots