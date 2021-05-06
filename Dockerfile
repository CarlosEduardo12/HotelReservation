FROM openjdk:11

ENV PROFILE=${PROFILE}

WORKDIR /opt/HotelReservation

COPY /target/HotelReservation*.jar HotelReservation.jar

SHELL ["/bin/sh","-c"]

EXPOSE 8080

CMD java -jar HotelReservation.jar --spring.profiles.active=${PROFILE}