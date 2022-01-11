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

If you want to use Docker to run your service, the api have a Dockerfile. 
To run it, just type:

```bash
$ docker-compose build

$ docker-compose up
```

* If you run into any permission issue, make sure you have execute permissions on ```api/entrypoint.sh``` (```chmod +x entrypoint.sh```)

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

Check the report: <path>/insurance-hub/<module>/build/reports/jacoco/test/html/index.html

### Coding checks

* To run the codeChecks

```bash
./gradlew codeChecks
```

## Built With

* [Java](https://www.java.com/) - Programming language
* [Gradle](https://gradle.org/) - Build automation tool
* [Spring](https://spring.io/) - Comprehensive programming and configuration model for modern Java-based applications
* [Spring Boot](https://spring.io/projects/spring-boot) - Spring Boot makes it easy to create stand-alone,
  production-grade Spring based Applications
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
* [git-properties](https://github.com/n0mer/gradle-git-properties) - Plugin that
  uses [spring-boot-actuator](https://github.com/spring-guides/gs-actuator-service) to give information about the
  current version that running through the endpoint `/actuator/info`.
