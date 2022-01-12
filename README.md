# Insurance Hub

This application is responsible for calculating insurance risk profiles based on users information.

The structure of this project is dedicated to an API project with a clean architecture, and it is organized in 2
modules:

1. `core`: All domain entities and use cases without dependency of Spring;
3. `api`: REST API endpoints. Depends on `core`.

## Requirements

* [Java 11](https://docs.oracle.com/en/java/javase/11/docs/api/)
* [Docker](https://docs.docker.com/engine/install)
* [Docker Compose](https://docs.docker.com/compose/install)

### Installing and running

* For easy java version management, i recommend using SDKMAN, you can look at the installation
  guide [here](https://sdkman.io/install) (Once installed you can download the java version used in this project)

````
# Install

$ sdk install java 11.0.9.hs-adpt

Installing: java 11.0.9.hs-adpt Done installing!

# Select the version

$ sdk use java 11.0.9.hs-adpt

Using java version 11.0.9.hs-adpt in this shell.

# Use it!

$ java --version

openjdk 11.0.9.1 2020-11-04 OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.9.1+1)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.9.1+1, mixed mode)
````

* For easy env switch, you can use:

````
$ sdk env
````

* If you are using IntelliJ, the code style configuration is automatically through **.editorconfig** file

* Build the project

```bash
cd <path>/insurance-hub/;
./gradlew clean build;
```

* Run the API project

```bash
./gradlew :api:bootRun
```

## Docker

If you want to use Docker to run your service, just type (since the build is done inside docker container, it's slower
than usual, so be aware of build time):

```bash
$ docker-compose build

$ docker-compose up
```

* If you're using MAC and run into gradle shutting down and running out of memory, try to increase docker memory
  availability, for more info read [here](https://docs.docker.com/desktop/mac/#resources).

## Running the tests

* To run tests:

```bash
./gradlew test
```

### Code Coverage

* To run the code coverage

```bash
./gradlew jacocoTestReport
```

Check the report: {path}/insurance-hub/{module}/build/reports/jacoco/test/html/index.html

### Coding checks

* To run the codeChecks

```bash
./gradlew codeChecks
```

## Open API V3

Using `springdoc-openapi` to automatically generate our OpenAPI v3 specification. To access it, you can run the API
project and then access it on:

```
http://localhost:8080/v3/api-docs
```

## Swagger

Using Swagger 2 to document our APIs with the generated OpenAPI v3 spec. To access it, you can run the API project and
then access it on:

```
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/rest-calculate-insurance-controller
```

By default, all the controllers will be mapped in this interface. It can be used to test endpoints and understand which
parameters are required to make a request to the API.

## Built With

* [Java](https://www.java.com/) - Programming language
* [Gradle](https://gradle.org/) - Build automation tool
* [Spring](https://spring.io/) - Comprehensive programming and configuration model for modern Java-based applications
* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone,
  production-grade Spring based Applications
* [Hamcrest](http://hamcrest.org/) - Framework for writing matcher objects allowing 'match' rules to be defined
  declaratively. There are a number of situations where matchers are invaluable, such as UI validation, or data
  filtering, but it is in the area of writing flexible tests that matchers are most commonly used
* [Checkstyle](https://docs.gradle.org/5.5.1/userguide/checkstyle_plugin.html) - Performs quality checks on your
  project’s Java source files using [Checkstyle](http://checkstyle.sourceforge.net/index.html) and generates reports
  from these checks
* [JaCoCo](https://docs.gradle.org/5.5.1/userguide/jacoco_plugin.html) - The JaCoCo plugin provides code coverage
  metrics for Java code via integration with [JaCoCo](https://www.eclemma.org/jacoco/)
* [PMD](https://docs.gradle.org/5.5.1/userguide/pmd_plugin.html) - Performs quality checks on your project's Java source
  files using [PMD](https://pmd.github.io/). It finds common programming flaws like unused variables, empty catch
  blocks, unnecessary object creation, and so forth. Additionally, include CPD (copy-paste-detector) that finds
  duplicated code.
* [Swagger](https://swagger.io/) - Enabling development across the entire API lifecycle, from design and documentation,
  to test and deployment
* [Spring-boot-actuator](https://github.com/spring-guides/gs-actuator-service) to give information about the current
  version that running through the endpoint `/actuator/info`.
