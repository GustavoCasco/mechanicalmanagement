package br.com.mechanicalmanagement.mechanicalmanagement.adapters.database.aws;

import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;
import software.amazon.awssdk.services.secretsmanager.model.SecretsManagerException;

public interface SecretsManagerService {

    static String getValue(SecretsManagerClient secretsClient, String secretName) {

        try {
            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName)
                    .build();
            GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
            return valueResponse.secretString();
        } catch (SecretsManagerException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            throw new RuntimeException(e.awsErrorDetails().errorMessage());
        }
    }
}
