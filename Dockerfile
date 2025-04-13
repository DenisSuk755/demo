
FROM jenkins/jenkins:alpine

USER root

Install Docker, curl, unzip, and both JDKs
RUN apk add --update \
    openrc \
    docker \
    openjdk11 \
    openjdk17 \
    bash \
    curl \
    unzip

Set Gradle version
ENV GRADLE_VERSION=7.6
ENV GRADLE_HOME=/opt/gradle

Download and install Gradle
RUN mkdir -p ${GRADLE_HOME} && \
    curl -fsSL https://services.gradle.org/distributions/gradle-$%7BGRADLE_VERSION%7D-bin.zip -o /tmp/gradle.zip && \
    unzip /tmp/gradle.zip -d /opt/gradle && \
    rm /tmp/gradle.zip

Add Gradle to PATH
ENV PATH="${GRADLE_HOME}/gradle-${GRADLE_VERSION}/bin:${PATH}"

Verify installation
RUN gradle -v