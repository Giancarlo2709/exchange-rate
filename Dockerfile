FROM adoptopenjdk/openjdk11:latest
RUN mkdir -p /opt/exchange
WORKDIR /opt/exchange
COPY target/exchange-rate-0.0.1-SNAPSHOT.jar /opt/exchange/target/
EXPOSE 8090
CMD ["sh", "-c", "java -jar /opt/exchange/target/exchange-rate-0.0.1-SNAPSHOT.jar"]