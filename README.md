Vedantu Assignment

This project provides a basic API for creating orders. 
Orders can have multiple Items in it.


Prerequisites
JAVA 8


Build
mvnw clean package


Run
java -jar target/Vedantu-0.0.1-SNAPSHOT.jar




For Creating Orders:-
API -> localhost:8080/createOrder
JSON -> {
	
	"userId": "123",
	"items":[{
		"id": 1,
		"count":10
		},]	
	}



For DB:-
API -> localhost:8080/h2-console
On JDBC opiton use "jdbc:h2:mem:assignment" and click connect



Important Notes:-
* Works with user id "123", to add more users modify data.sql accordingly.
* Currently the application supports 4 items with id 1,2,3,4. For adding or deleting refer to data.sql file.


 


