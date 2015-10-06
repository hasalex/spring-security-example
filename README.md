Spring Security Example
====

Basic authentication
----

Launch the application 

    mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=basic"

Launch the client, with curl

    curl -u user:pwd http://localhost:8080/ping

Launch the client, with httpie

    http -ba user:pwd http://localhost:8080/ping


Digest authentication
----

Launch the application 

    mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=digest"

Launch the client, with curl

    curl --digest -u user:pwd http://localhost:8080/ping

Launch the client, with httpie

    http --auth-type=digest -ba user:pwd http://localhost:8080/ping

Digest authentication with encoded passwords
----

Launch the application 

    mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=encoded"

Launch the client, with curl

    curl --digest -u user:pwd http://localhost:8080/ping

Launch the client, with httpie

    http --auth-type=digest -ba user:pwd http://localhost:8080/ping
