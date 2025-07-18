package it.epicode.W7_D2_BE.service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("application.properties")
public class AppConfig {

    @Bean
    public Cloudinary getCloudinary(@Value("${cloudinary.cloud_name}") String cloudName, @Value("${cloudinary.api-key}") String apiKey, @Value("${cloudinary.api_secret}") String apiSecret){
        Map<String, String> configCloudinary = new HashMap<>();

        configCloudinary.put("cloud_name", cloudName);
        configCloudinary.put("api_key", apiKey);
        configCloudinary.put("api_secret", apiSecret);

        return new Cloudinary(configCloudinary);
    }

}
