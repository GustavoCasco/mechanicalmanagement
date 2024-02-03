package br.com.mechanicalmanagement.mechanicalmanagement.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Value("${aws.secrets.dtbase-user}")
    private String secretDtbaseUser;
    @Value("${aws.secrets.dtbase-pwd}")
    private String secretDtbasePwd;
    @Autowired
    private SecretsManagerClient secretsManagerClient;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/databasecasco");
        hikariConfig.setUsername(getSecret(secretDtbaseUser));
        hikariConfig.setPassword(getSecret(secretDtbasePwd));
        return new HikariDataSource(hikariConfig);
    }

    private String getSecret(String secretName){
        try {
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();
            GetSecretValueResponse valueResponse = secretsManagerClient.getSecretValue(valueRequest);
            return valueResponse.secretString();
        } catch (SecretsManagerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            throw new RuntimeException(e.awsErrorDetails().errorMessage());
        }
    }
}