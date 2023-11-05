# Builder
FROM alpine:3.17 AS build
WORKDIR /source
RUN apk add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
COPY . .
RUN ./mvnw package -Dmaven.test.skip=true

# Run the application
FROM alpine:3.17
WORKDIR /app
RUN apk add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
COPY --from=build /source/target/*.jar ConfigProvider.jar
ENTRYPOINT ["java", "-jar", "/app/ConfigProvider.jar"]
