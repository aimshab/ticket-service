# ticket-service
This project create a simple ticketing service which enables the users to find how many available seats, hold seats and reserve seats. The hold has an expiration time which will invalidate the hold and let other users find and reserve those seats.

This a Maven based project and the service is verified through a set of unit test that could be found in src/test/java

To be able to build and run the unit tests you need:
1. JDK 8 
2. Maven 3.2 or newer
3. (make sure you have your Java and Maven home setup correctly)

To build and run unit test, execute this command from the ticket-srevice directory where the pom.xml file is located
mvn clean install

