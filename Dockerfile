# Starting from the Openjdk-8 container
FROM java:openjdk-8-jdk

# Set the WORKDIR. All following commands will be run in this directory.
WORKDIR /app

# Copying all gradle files necessary to install gradle with gradlew
COPY gradle gradle
COPY \
  build.gradle \
  gradle.properties \
  gradlew \
  settings.gradle \
  ./

# Install the gradle version used in the repository through gradlew
RUN ["ls"]
# Run gradle assemble to install dependencies before adding the whole repository
#RUN gradle assemble

ADD . ./
ADD src ./

EXPOSE 8080

RUN ["./gradlew", "run"]
