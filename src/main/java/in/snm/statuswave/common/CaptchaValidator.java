package in.snm.statuswave.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CaptchaValidator {
    @Value("${hCaptcha.secret.key}")
    private String hCaptchaSecretKey;

    private final RestTemplate restTemplate;

    public boolean validateCaptcha(String captchaResponse) {
        String hCaptchaUrl = "https://hcaptcha.com/siteverify";

        // Prepare the form parameters
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("secret", hCaptchaSecretKey);
        params.add("response", captchaResponse);

        // Send the POST request to hCaptcha
        ResponseEntity<String> response = restTemplate.postForEntity(hCaptchaUrl, params, String.class);

        // Log response for debugging
        System.out.println("hCaptcha Response Status: " + response.getStatusCode());
        System.out.println("hCaptcha Response Body: " + response.getBody());
        boolean result = response.getBody() != null && response.getBody().contains("\"success\":true");
        System.out.println("RESULT: " + result);
        // Check if the response contains the success flag
        return result;
    }
}
