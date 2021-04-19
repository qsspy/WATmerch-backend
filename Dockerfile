FROM openjdk:latest
ADD target/watmerch-backend.jar watmerch-backend.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","watmerch-backend.jar"]