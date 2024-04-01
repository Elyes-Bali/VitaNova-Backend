package tn.esprit.vitanova.config;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class CloudinaryConfig {

    @Value(value = "${app.cloudinary.cloud_name}")
    private String cloudName;

    @Value(value = "${app.cloudinary.apikey}")
    private String apiKey;

    @Value(value = "${app.cloudinary.appsecret}")
    private String apiSecret;

    @Bean
    public Cloudinary initCloudinary() {
        log.info("init cloudinary config");
        Map<String, Object> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", cloudName);
        valuesMap.put("api_key", apiKey);
        valuesMap.put("api_secret", apiSecret);
        valuesMap.put("secure", true);
        return new Cloudinary(valuesMap);
    }
}
