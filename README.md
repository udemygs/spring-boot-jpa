# spring-boot-jpa - Small Getting Started Project

**1. Pre-Requisite**

From `pom.xml`, maven dependencies.

```
 Spring Boot Started
 Embeded Tomcat
 Mysql(Kindly download and install first)
 Java 1.8
 Swagger 2.7.0 
 Junit 5.7.0 - Jupiter
```
**2. Configuration:**

1. Configurate Data Source at `application.properties`, set the `<SCHEMA>`

   Eg. spring.datasource.url=jdbc:mysql://localhost:3306/<SCHEMA>?useSSL=false&useTimezone=true&serverTimezone=UTC

**3. Run the spring boot program:**

``` $ ./mvnw spring-boot:run```

Server started: Starting MovieApplication using Java ...

**4. Test the REST API by using Postman tool(or Tabbed Postman Chrome-Extension)**

Use swagger to checkout the REST API URI:
`http://localhost:8080/swagger-ui.html`



