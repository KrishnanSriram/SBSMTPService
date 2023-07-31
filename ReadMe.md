# SMTP Service with SpringBoot

## Description
This is a simple service built with Java 11 in reactive fashion. Uses Webflux to acheive reactive behavior

## Execution
To execute this service from command prompt build jar file and use the following command
```shell
java -jar -Dspring.mail.host=smtp.gmail.com -Dspring.mail.port=587 -Dspring.mail.username=someuser@gmail.com -Dspring.mail.password=somepassword SMTPService-0.0.1-SNAPSHOT.jar
```