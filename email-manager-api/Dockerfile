FROM openjdk:17-alpine
EXPOSE 8084
VOLUME /tmp
RUN mkdir /work
COPY . /work
WORKDIR /work
RUN /work/gradlew build
RUN mv /work/build/libs/ema-0.0.1-SNAPSHOT.jar /work/app.jar
ENTRYPOINT ["java","-jar","/work/app.jar"]