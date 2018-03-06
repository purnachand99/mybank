# mybank
Challenge App for Viventor to demonstrates a Banking Application using Spring Boot, JPA and Restful Services.

## Architecture Definition

I used the [Classic Way](https://www.petrikainulainen.net/software-development/design/understanding-spring-web-application-architecture-the-classic-way/) with 3 layers to define the application architecture as described below:

**The web layer** is the uppermost layer of a web application. It is responsible of processing user’s input and returning the correct response back to the user. The web layer must also handle the exceptions thrown by the other layers. Because the web layer is the entry point of our application, it must take care of authentication and act as a first line of defense against unauthorized users.

**The service layer** resides below the web layer. It acts as a transaction boundary and contains both application and infrastructure services. The application services provides the public API of the service layer. They also act as a transaction boundary and are responsible of authorization. The infrastructure services contain the “plumbing code” that communicates with external resources such as file systems, databases, or email servers. Often these methods are used by more than a one application service.

**The repository layer** is the lowest layer of a web application. It is responsible of communicating with the used data storage.

![alt text](http://www.petrikainulainen.net/wp-content/uploads/spring-web-app-architecture.png)

## Rest Api

After you run this appication, you can see all operations of the **Rest API** acessing [here](http://localhost:8080/swagger-ui.html)

_Obs.: I used Swagger2 for Rest API documentation_

## Running in Development (with LiveReload)

Open two terminals:

1. At the first terminal, start Gradle build as a continuous task: gradle build --continuous
2. At the second terminal, start the Gradle bootRun task: gradle bootRun


## Unit and Integrated Tests

In development...