# Use the official Keycloak image
FROM quay.io/keycloak/keycloak:latest

# Switch to root user to copy the JAR file and configuration files
USER root

# Create the "providers" directory if it doesn't exist
RUN mkdir -p /opt/keycloak/providers/

# Copy the custom provider JAR file into the container
COPY target/storage-0.0.1-SNAPSHOT.jar /opt/keycloak/providers/

# Copy keycloak.conf
COPY conf/quarkus.properties /opt/keycloak/conf/

# Ensure the "keycloak" user has access to the "providers" directory and configuration files
RUN chown -R keycloak:keycloak /opt/keycloak/providers/ /opt/keycloak/conf/

# Switch back to the "keycloak" user to run Keycloak
USER keycloak
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin123

# Start Keycloak
ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start-dev"]