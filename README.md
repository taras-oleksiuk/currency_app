# currency_app
# General info
It's the implementation of a movie exchange application. The project uses Spring boot and Hibernate frameworks. 
The project is built using a SOLID, three-tier architecture (Repository, Service, and Controller levels), and REST principles. 
Users can have two roles: admin or user. 


## HTTP methods: 
* POST: /registration - all
* GET: /search - user/admin
By this method user can get:
* the page with the defined base currency and according to exchanges (an example: /search/JPY or /search/CNY);
* the page with the defined history of exchanges history (an example: /search/2020-10-01/2021-12-03)
* GET: /exchanges - user/admin

## Technologies
* Java 11
* Spring (Spring boot, Spring Web, Spring Security)
* Hibernate
* JUnit 5
* log4j
* H2
* Apache Tomcat 9.0.54
* Maven

## The sequence of HTTP methods
1. POST /api/v1/registration
{
    "firstName": "Bob",
    "lastName": "Smith",
    "email": "bob@gmail.com",
    "password": "password"
}
You will got a token.
2. GET /api/v1/registration/confirm?token=YOUR_TOKEN
You will got a message: Confirmed
3. Logged in the app /login
4. Use the program
