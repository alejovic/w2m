FROM openjdk:11
EXPOSE 8080
ADD target/w2m-0.0.1-SNAPSHOT.jar w2m-pruebatecnica.jar
ENTRYPOINT ["java","-jar","w2m-pruebatecnica.jar"]

# docker build -t w2m-pruebatecnica
# docker run -p 9090:8080 w2m-pruebatecnica --name w2m-pruebatecnica
