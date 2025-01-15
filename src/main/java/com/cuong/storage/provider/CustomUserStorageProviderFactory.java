package com.cuong.storage.provider;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.cuong.storage.constants.JpaStorageProviderConstants.ADD_ROLES_TO_TOKEN;
import static com.cuong.storage.constants.JpaStorageProviderConstants.PROVIDER_NAME;

public class CustomUserStorageProviderFactory implements UserStorageProviderFactory<CustomUserStorageProvider> {
    private static final Logger logger = Logger.getLogger(String.valueOf(CustomUserStorageProviderFactory.class));
    protected final List<ProviderConfigProperty> configMetadata;

    public CustomUserStorageProviderFactory() {
        this.configMetadata = ProviderConfigurationBuilder.create()
                .property().name(ADD_ROLES_TO_TOKEN).label("Add Roles to Token").type(ProviderConfigProperty.BOOLEAN_TYPE).defaultValue(true).helpText("Add roles to token. This will help you to use roles in your application.").required(true).add()
                .build();
    }

    @Override
    public CustomUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new CustomUserStorageProvider(session, model);
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configMetadata;
    }
}