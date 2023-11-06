# Builder
FROM alpine:3.17 AS build
WORKDIR /source
RUN apk add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
COPY . .
RUN ./mvnw package -pl Accounts -Dmaven.test.skip=true

# Run the application
FROM alpine:3.17
WORKDIR /app
RUN apk add openjdk11 --repository=http://dl-cdn.alpinelinux.org/alpine/edge/community
RUN apk update && apk add jq
RUN apk --no-cache add curl 
COPY --from=build /source/Accounts/target/*.jar Bookings.jar