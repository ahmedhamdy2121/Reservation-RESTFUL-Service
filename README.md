# EA Project
[![Build Status](https://dev.azure.com/ahmedahamdy90/EA%20Project/_apis/build/status/EA_Project?branchName=master)](https://dev.azure.com/ahmedahamdy90/EA%20Project/_build/latest?definitionId=2&branchName=master)

Repository link: [https://dev.azure.com/ahmedahamdy90/EA%20Project](https://dev.azure.com/ahmedahamdy90/EA%20Project).

# Introduction
Our project is mainly divided with the best practice architecture, it has the following design:

 - config
	 - domain enumeration
	 - exception
	 - security
 - controller
 - domain
 - repository
	 - search
 - service
	 - security dto
	 - mapper
	 - request
	 - response
	 - validator

We have created a base repository, base service, and base controller, which all the controller should inherit from, some the controllers can get some benefits from this base controller like the filtering functionality and can reuse the base service (we faced a problem with Spring injection of a generic service). We also have developed a base mapper which all the mappers are using to map between domain, request, and response.

The response package contains all the response objects that can be returned to the user, that way we are separating our domain from the higher level, we also created Null Object Design Pattern to handle a null exception and send a message to the client.

Our request objects have only the modifiable data, and it is being validated before the front controller sends it to our controller, after that our controllers are serving the request by contacting the service layer. **Note that the controllers only deal with the request and response objects, they don't know anything beyond the service layer**.

Our domain is consisting of 4 entities, which are reservation, appointment, user, and user role, and we have added 2 levels of validation here, one on the DB and the other validation on the application side. So in total, we have 3 layers of validation (service layer, domain layer, DB).

For the first time to run the project, please check the **Build and Test** section, at first we should allow hibernate to create the schema tables, then we should allow our data seeder class to run (using event listener) to fill the database with some data, especially calling the signup function for all the added users, as we are using hashed passwords. Also, our system authorizes all the user's restful calls throughout his access token.

## Normal life cycle of the project

We have selected this scenario only to show here, but we are supporting multiple scenarios, **this scenario is a student registering for the system, creating a reservation, gets an acceptance on his reservation**:
 1. The user signs up to an account.
 2. The admin adds a role "student" for the user.
 3. The user signs in the system.
 4. The system authenticates the user and approves it, and sends an access token which will be needed for all the restful calls.
 5. The user views all the available appointment, the user can also filter appointments by date, checker, ...etc.
 6. The system checks the authorization of the user and grant him permission to view the data.
 7. The user creates a reservation for an appointment.
 8. The system checks the authorization of the user and grant him permission to view the data.
 9. The system will create a reservation with a default status pending and a default current time.
 10. The checker user gets a list of all reservations per appointment sorted by date.
 11. The checker user approves this student reservation.
 12. The system will send an email to both the checker and the students.

## Technologies used

 1. Azure DevOps for CI/CD control.
 2. Spring Boot with devtools.
 3. Spring Web.
 4. Spring Security.
 5. Spring Data JPA with MySQL.
 6. Spring Validation.
 7. Java Mail Sender.
 8. Spring Swagger.
 9. Model Mapper.
 10. Parts of the Professor's Framework. 

## Bonus features

 1. Implemented administrative functionalities like sign in and signup.
 2. Used Azure DevOps (Pipeline and User Stories).
 3. Used Swagger for security.
 4. Did a proof of concept on async and email queue email engine.
 5. A well designed and architected project.
 6. Did a proof of concept on the reminder email.
 7. Separation of concern as the email service is an aspect.

# Build and Test

## For the first time only

1. Create the DB user and schema.

2. Enable Hibernate create mode, in `yml` file uncomment `ddl-auto: create`.

3. Uncomment the annotation `@EventListener` in `DataSeeder` class to put some data in the DB.

  

**Starting from the second run, you need to comment the code in step 2 and 3 again.**

  

# Getting Started

1. Use this link to get a token [security login](http://localhost:8080/swagger-ui.html).

2. Use Postman to test it.