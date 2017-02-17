# Rest-sample
Rest Sample was created to show basic REST operations using Spring MVC and MongoDB.

# Technologies used

Java 1.8

Maven 3.3.9

Spring boot 1.4.1

MongoDB 3.2

Swagger 2.6.1

# Installing and testing

Type `mvn spring-boot:run` from the root project directory to start the application;

Check the Swagger API: http://localhost:8080/swagger-ui.html

The MongoDB used was pulled on the docker official repository: https://hub.docker.com/_/mongo/

The command used to pull and run the docker image was:

``docker run --name mongo-users -d -p 27017:27017 mongo``

# URLs

To see all the endpoints created, please check it trough Swagger documentation:

Swagger: http://localhost:8080/swagger-ui.html

# Contact

Lucas Montanari (lucas_montanari@hotmail.com)
