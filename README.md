# Callcenter - Auth Service

## Introduction

### About the previous project - [Callcenter project](https://github.com/MiwaUS7605/fit_20clc_hcmus_software_architecture) and [callcenter_microservices](https://github.com/PHoaiLe/call_center_microservices)

Callcenter is a system that provides a mobile app interface to clients to book plenty of pickups, 
a mobile app interface to drivers, and a desktop interface to operators who record, and monitor pickup’s status. 
<br/>

It started on 2023/07/16 and be closed on 2023/10/08. The project meet the demand of academic level,
which was built on Android OS minimum SDK is API 28, by Java and Android Studio. 
The desktop app was built on .NET, C#. The Server was built on Spring-Spring Boot v3.0.5, 
supported by using Maven and Firebase SDK Admin, and applied Event-Driven architecture by using Apache Kafka, local repository
dependency, FCM for notification, and microservice architecture.
<br/>

By some reasons, the team used Firebase Cloud Services as the major service to handle Authentication and Authorization Services.
This approach provided fast and convenient solution for us to quickly apply into our system, which accelerated the implementation
process and meet requirements of the course at that time.

### Why I make this Auth Service repository?
Since the [callcenter_microservices](https://github.com/PHoaiLe/call_center_microservices) focused on how services/components in the system connect together and the way that requests/messages 
from the client are sent through the services in a business executing process by applying Event-Driven Architecture thanks to Apache Kafka implementation.
This time, a self-study project, we are going to alter some components depending on the Firebase services and also the Firebase Database 
by self-implementation elements that provide the same roles/functions. Obviously, although this creates some problems/questions that we have to solve in the process of implementation,
it also gives us chances to face some challenges that we were lucky to be solved by third-party services.

## Expectations
As a self-study project, the intended aim is to concentrate on a well-built service as much as possible that can be applied in the production environment, 
which requires qualified knowledge and skill in understanding the framework, technologies,... and how we design and implement the system.
I'm also willing to learn and apply new technologies to this project to improve it so there are expectations below
<br/>

- Create a well-built Auth Service
- The service source code can be easily extended in the future
- The service will be deployed on AWS EC2
- Gaining comprehensive knowledge of authentication and authorization in a system
- Knowledge of Spring (Spring Boot) framework
- .etc
<br/>

## Requirements
