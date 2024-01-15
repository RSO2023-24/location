FROM eclipse-temurin:17-jre

RUN mkdir /app

WORKDIR /app

ADD ./api/target/location-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8090

CMD ["java", "-jar", "location-api-1.0.0-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "location-api-1.0.0-SNAPSHOT.jar"]
#CMD java -jar location-api-1.0.0-SNAPSHOT.jar
