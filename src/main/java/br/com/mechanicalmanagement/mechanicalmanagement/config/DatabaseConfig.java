package br.com.mechanicalmanagement.mechanicalmanagement.config;

import br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.aws.SecretsManagerService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

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
        hikariConfig.setUsername(SecretsManagerService.getValue(secretsManagerClient, secretDtbaseUser));
        hikariConfig.setPassword(SecretsManagerService.getValue(secretsManagerClient, secretDtbasePwd));
        return new HikariDataSource(hikariConfig);
    }

}