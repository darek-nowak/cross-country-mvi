# What is a goal of the project
Create an application that fetches data from countries REST api and present them first
in list and then in detail screen for each country if requested

# Rules of collaborations
Don't change names of files, variables if they were already changed
Do not remove or edit comments not related to current task that you are doing

# Architecture
Version Cataloge will be used to manage dependencies 

Clean Android architecture will be used with all clearly defined layers and their responsibilities:
- UI Screens
- ViewModel
- Domain UseCases
- Repository pattern
- Retrofit Remote Source
Data Models will be mapped between layers

Coroutines will be used for asynchronous operations

MVI and UDF will be used for state management

# What UI is used
Jetpack Compose with side effects will be used for presentation
