# `Vistors Counter`

## The goal of the program
Java application, that counts users visited our web-page from different sources.
Mostly filled with duplicates. A unique user is identified via unique phone and email combination.
## Swagger UI
```bash
 http://localhost:8080/swagger-ui.html
```

## Notes
- The application is testable, feel free to run the tests 
- The application is configurable,
- The application is using swagger.
- /generate endpoint is used to generate big size mock files
- /files get the files names
- /count endpoint is used to get the number of visitors
- You can read the files using two different functionalities (Faster/Slower).
- User can choose which functionality he needs to use. default is (Faster)

## Build instructions

```bash
# install gradle wrapper
> gradle wrapper

# build
> ./gradlew build

# build without tests
> ./gradlew build -x test

# run application from the command line
> ./gradlew bootRun

# clean the build
> ./gradlew clean
```
