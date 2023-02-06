# pharmacies-duty-backend

Service that returns the pharmacies that are on duty according to specific filters.

It gets the information from MINSAL's API (https://farmanet.minsal.cl/index.php/ws/getLocalesTurnos), 
because the API from challenge didn't work.

This service let you filter the information with 2 parameters:

* storeName: Name of the pharmacy 
* commune: Id number of commune  

It's implements a controller to expose DTO objects, a service to implement de business logic and a Client to get the data from the API.

# How to run

It can run with maven command
```
.\mvnw clean spring-boot:run
```
or using the Dockerfile

# How to try it locally

With parameters
```
curl --location --request GET 'http://localhost:8080/pharmacies?storeName=CRUZ VERDE&commune=56'
```

Without parameters
```
curl --location --request GET 'http://localhost:8080/pharmacies'
```