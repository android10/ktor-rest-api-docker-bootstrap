# Pull open jde base image based on Alpine Linux.
FROM openjdk:8-jre-alpine

# Define environment variables
ENV ARTIFACT_ID bestoke-backend.jar
ENV ARTIFACT_BUILD_DIR ./build/libs
ENV ARTIFACT_EXEC_DIR /bestoke
ENV APPLICATION_USER ktorapp
ENV PORT 5000
ENV PORT_SSL 8443

# TODO: This is a temporary certificate for SSL
ENV SSL_CERTIFICATE temporary.jks
ENV SSL_CERTIFICATE_BUILD ./build/$SSL_CERTIFICATE

# Ports intedended to be published
EXPOSE $PORT
EXPOSE $PORT_SSL

# Define user to execute application.
RUN adduser -D -g '' $APPLICATION_USER

# Define working directory
RUN mkdir -p $ARTIFACT_EXEC_DIR

# Grant permission to working directory.
RUN chown -R $APPLICATION_USER $ARTIFACT_EXEC_DIR

# Set execution user.
USER $APPLICATION_USER

# Copy the current directory contents into the container.
COPY $ARTIFACT_BUILD_DIR/$ARTIFACT_ID $ARTIFACT_EXEC_DIR/$ARTIFACT_ID
WORKDIR $ARTIFACT_EXEC_DIR

# TODO: This is a temporary certificate for SSL
# Copy the self-signed SSL Certificate
COPY $SSL_CERTIFICATE_BUILD $ARTIFACT_EXEC_DIR/$SSL_CERTIFICATE

# Command to execute.
CMD java -server \
    -XX:+UnlockExperimentalVMOptions \
    -XX:+UseCGroupMemoryLimitForHeap \
    -XX:InitialRAMFraction=2 \
    -XX:MinRAMFraction=2 \
    -XX:MaxRAMFraction=2 \
    -XX:+UseG1GC \
    -XX:MaxGCPauseMillis=100 \
    -XX:+UseStringDeduplication \
    -jar $ARTIFACT_ID \