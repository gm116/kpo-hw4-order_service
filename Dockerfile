FROM openjdk:17

ADD /build/libs/orderservice-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
