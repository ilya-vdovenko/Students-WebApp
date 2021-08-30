# Students-WebApp

![SonarCloud](https://github.com/ilya-vdovenko/Students-WebApp/workflows/SonarCloud/badge.svg)
![Build and deploy](https://github.com/ilya-vdovenko/Students-WebApp/workflows/Build%20and%20deploy/badge.svg)
![Lint Code Base](https://github.com/ilya-vdovenko/Students-WebApp/workflows/Lint%20Code%20Base/badge.svg)
[![Open in Visual Studio Code](https://open.vscode.dev/badges/open-in-vscode.svg)](https://open.vscode.dev/ilya-vdovenko/Students-WebApp)

This repo is a Pet-project of web application represent list of students from database (HSQLDB, MySQl), with a java based
spring framework configuration (a plain old **XML** configuration [here](https://github.com/ilya-vdovenko/Students-WebApp/tree/master)) and with a **3-layer architecture** (i.e. presentation --> service --> repository).
Created by following the example of this repository [spring-projects/spring-framework-petclinic](https://github.com/spring-petclinic/spring-framework-petclinic).

## Running web-app locally

### With Maven command line

```cmd
git clone https://github.com/ilya-vdovenko/Students-WebApp.git
cd Students-WebApp
./mvnw jetty:run-war
# For Windows : ./mvnw.cmd jetty:run-war
```

You can then access app here: localhost:8080/Students-WebApp

## Working with in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (<https://help.github.com/articles/set-up-git>)
* Your preferred IDE
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in Help -> About dialog. If m2e is not there, just follow the install process [here](http://www.eclipse.org/m2e/)
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
  * Visual studio code

### Steps

1) Import project and past url to the project. Or on the command line

```cmd
git clone https://github.com/ilya-vdovenko/Students-WebApp.git
```

2) (If through CL) Inside IDE

```text
File -> Import -> Maven -> Existing Maven project
```

3) After import, click `jetty:run` from maven window, or type in IDE command line `mvn jetty:run`

4) Visit localhost:8080/Students-WebApp in your browser.

## Database configuration

By default, Students-WebApp uses an in-memory database (HSQLDB) which gets populated at startup with data.
A similar setup is provided for MySQL in case a persistent database configuration is needed.
To run web-app locally using persistent database, it is needed to run with profile defined in main pom.xml file.

For MySQL database, it is needed to run with 'MySQL' profile defined in main pom.xml file.

```cmd
./mvnw jetty:run-war -P JDBC_MySQL
```

Before do this, would be good to check properties defined in MySQL profile inside pom.xml file.

```xml
<properties>
      <maven.spring.profiles.active>jdbc</maven.spring.profiles.active>
      <maven.skipTests>true</maven.skipTests>
      <maven.db.script>mysql</maven.db.script>
      <maven.jpa.database>MYSQL</maven.jpa.database>
      <maven.jdbc.driverClassName>com.mysql.cj.jdbc.Driver</maven.jdbc.driverClassName>
      <maven.jdbc.url>jdbc:mysql://localhost:3306</maven.jdbc.url>
      <maven.jdbc.username>institute</maven.jdbc.username>
      <maven.jdbc.password>institute</maven.jdbc.password>
</properties>
```

You can use `docker-compose.yml` file for run MySQL. Be sure that you had install [Docker-compose](https://docs.docker.com/compose/install/).

```cmd
docker-compose -f "docker-compose.yml" up -d --build
```

## License

Students-WebApp is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
