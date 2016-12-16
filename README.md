![Build status](https://api.travis-ci.org/Qerts/pa165-java.svg?branch=master)

# Flee Management

Application for evidence of company's vehicles. See [Wiki pages](https://github.com/Qerts/pa165-java/wiki) for detailed information.

## Installation

Assuming you have already installed [Java 1.8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) and [Maven](https://maven.apache.org/),
checkout the repository and run command `mvn pacakge` from command line to download all dependencies and run the tests.

To run web application, execute `cd fleet-management-spring-mvc && mvn tomcat7:run` GUI application will be available on [http://localhost:8080/pa165](http://localhost:8080/pa165).
and API will we available on [http://localhost:8080/pa165/rest](http://localhost:8080/pa165/rest) with following endpoints:

- `/vehicles` - lists all vehicles

## Development

The Project is developed using a [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/). Use `Open Project` dialog to load project and develop comfortably.
