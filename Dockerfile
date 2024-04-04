FROM openjdk:17-jdk-slim
EXPOSE 8080 5433
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]

# Set environment variables for PostgreSQL connection
ENV DB_HOST=localhost
ENV DB_PORT=5432
ENV DB_NAME=userappointment
ENV DB_USER=postgres
ENV DB_PASSWORD=password

# (Optional) Install PostgreSQL client
RUN apt-get update && apt-get install -y postgresql-client
