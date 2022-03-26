## VISITORS LOG SERVICE

A visitors log microservice application that logs visitors visiting an organization. The service is written in Java Spring boot and containerized with docker.

### ENDPOINTS

The Service exposes these endpoints:

    a. POST /login - takes username and password
    b. POST /staff - Adds new staff - should take staff_name, phone_number, email_address and address
    c. GET /staff - Gets all Staff
    d. GET /staff/{id} - Get specific Staff
    e. POST /visitor - Adds new visitor - should take visitor_name, phone_number, email_address and address
    f. GET /visitors - Get visitors
    g. GET /visitor/{id} - Get specific visitor
    h. POST /visit - Logs new visit – should take visitor_id, staff_id, date_of_visit, reason_for_visit

#### Setup
Set JAVA_HOME
Set M2_HOME
Add M2_HOME/bin to the execution path

#### Docker commands - Steps to run the application
Clone the project to your machine.
##### Build jar
``
mvn package -DskipTests
``
##### Run Docker compose to bring up the required containers
``
docker-compose up
``
##### view all running containers
``
docker ps
``
##### Enter Docker MySQL container to create the visitorservice schema
``
docker exec -it docker-mysql bash
``
##### create mysql.infoschema user if it is not already existing in the database
``
mysql> CREATE USER 'mysql.infoschema'@'localhost' IDENTIFIED BY 'password';
Query OK, 0 rows affected (0.00 sec)
``
##### grant mysql.infoschema user privileges
``
mysql> GRANT SELECT ON *.* TO `mysql.infoschema`@`localhost`;
Query OK, 0 rows affected, 1 warning (0.00 sec)
``
##### create database
``
mysql> CREATE database visitorservice;
Query OK, 0 rows affected, 1 warning (0.00 sec)
``
##### Enter the applications Docker container shell to confirm it is started fine
``
docker exec -t -i visitorslog-docker /bin/bash
``
### SWAGGER URL - try it out

Find the swagger documentation <a href="http://localhost:8090/swagger-ui/index.html">here</a>.
