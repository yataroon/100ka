FROM payara/server-full:6.2025.6-jdk21

COPY app/target/*.war /opt/payara/deployments/

EXPOSE 8080 4848 9009

CMD ["asadmin", "start-domain", "--verbose"]