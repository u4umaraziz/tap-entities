# Merchant Entities Microserivce
This is entity microservice which have responsibility to create/update/get/delete merchant entities and do interact with other microservices like tax, bank, segment, notifications etc...

### Setup Instructions
Run redis docker container locally using following command first </br>
* docker run -d --name redis -p 6379:6379 redis

### Maven Install
* mvn clean install

### Docker
* docker build -t entities . </br>
* docker run -p 8080:8080 entities


### Service Details 
* Java 17
* SpringBoot 3.1.1
* Spring Data
* H2-In Memory Database
* Spring Open API documentation
* Micrometer 
* Zipkin

### Exclusions but should be part of this service
* Spring Security (Skipped)
* Kafka Configuration (Messaging with other services)
* FeignClient/Rest Client (Where sync communication is required)


### Get information about system health, configurations, etc.
http://localhost:8080/tap-entities/actuator/health

### Swagger URL
http://localhost:8080/tap-entities/swagger-ui/index.html#/

### H2-Console
http://localhost:8080/tap-entities/h2-console

### Zipkin Server
* For distributed logs and tracing we will be <br /><br />
docker run -p 9411:9411 openzipkin/zipkin:latest <br />
http://localhost:9411/zipkin 

### Spring Security
* All endpoints are ignored in application.properties files for testing purpose. But we will have authenticationFilter here which will be responsible to authneticate and authorise requests.
* JWT decoding will be handled in a filter and @PreAuthorize will be applied on each of endpoint based on the roles. 

### Spring Redis Cache
* Spring redis repositories are used to save or get from cache. For testing redis configuration test docker containers are used. 
