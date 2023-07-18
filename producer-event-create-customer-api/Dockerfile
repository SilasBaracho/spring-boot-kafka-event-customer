FROM openjdk:17-alpine
EXPOSE 8082
VOLUME /tmp
RUN mkdir /work
COPY . /work
WORKDIR /work
RUN /work/gradlew build
RUN mv /work/build/libs/pdc.event.create.customer.api-0.0.1-SNAPSHOT.jar /work/app.jar
ENTRYPOINT ["java","-jar","/work/app.jar"]