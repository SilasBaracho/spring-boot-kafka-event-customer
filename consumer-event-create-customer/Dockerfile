FROM openjdk:17-alpine
EXPOSE 8083
VOLUME /tmp
RUN mkdir /work
COPY . /work
WORKDIR /work
RUN /work/gradlew build
RUN mv /work/build/libs/csm.event.create.customer-0.0.1-SNAPSHOT.jar /work/app.jar
ENTRYPOINT ["java","-jar","/work/app.jar"]