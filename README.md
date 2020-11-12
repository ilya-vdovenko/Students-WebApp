# Students-WebApp
[![Mega-Linter](https://github.com/ilya-vdovenko/Students-WebApp/workflows/linter/badge.svg)](https://github.com/nvuillam/mega-linter#readme)
![Java maven CI](https://github.com/ilya-vdovenko/Students-WebApp/workflows/Java%20maven%20CI/badge.svg)

This repo is a Pet-project of web application represent list of students from database (HSQLDB only. More soon), with a plain old **Spring Framework configuration**
and with a **3-layer architecture** (i.e. presentation --> service --> repository). Created following the example of this repository [spring-projects/spring-framework-petclinic](https://github.com/spring-petclinic/spring-framework-petclinic)

## Running app locally

### With Maven command line

```
git clone https://github.com/ilya-vdovenko/Students-WebApp.git
cd Students-WebApp
./mvnw tomcat:run-war
```

You can then access app here: [http://localhost:8080/](http://localhost:8080/)


## Working with in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
  not there, just follow the install process here: https://www.eclipse.org/m2e/
  * IntelliJ IDEA

### Steps:

1) Import project and past url to the project. Or on the command line

```
git clone https://github.com/ilya-vdovenko/Students-WebApp.git
```

2) (If through CL) Inside IDE

```
File -> Import -> Maven -> Existing Maven project
```

3) After import, click `tomcat:run` from maven window, or type in IDE command line `mvn tomcat:run`

4) Visit [http://localhost:8080](http://localhost:8080) in your browser.


## License

Students-WebApp is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0).
