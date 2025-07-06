FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app
COPY app/ .
RUN mvn clean package -DskipTests

FROM payara/server-full:6.2025.6-jdk21

COPY --from=builder /app/target/*.war /opt/payara/deployments/

EXPOSE 8080 4848 9009

CMD ["asadmin", "start-domain", "--verbose"]