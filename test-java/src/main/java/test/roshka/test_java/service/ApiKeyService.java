package test.roshka.test_java.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiKeyService {
    
    private final List<String> validApiKeys;
    
    public ApiKeyService(@Value("${app.api.keys}") String apiKeys) {
        this.validApiKeys = Arrays.asList(apiKeys.split(","));
    }
    
    public boolean isValidApiKey(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            return false;
        }
        return validApiKeys.contains(apiKey.trim());
    }
}