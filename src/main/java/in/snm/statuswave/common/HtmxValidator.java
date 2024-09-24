package in.snm.statuswave.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.github.wimdeblauwe.htmx.spring.boot.mvc.HtmxRequest;

public class HtmxValidator {
    public static void validateHtmxRequest(HtmxRequest htmxRequest, String requiredUrl) {
        boolean isHtmxRequest = htmxRequest.isHtmxRequest();
        String currentUrl = htmxRequest.getCurrentUrl();

        // Check if the HTMX header exists and current URL contains the required URL fragment
        if (!isHtmxRequest || currentUrl == null || !currentUrl.contains(requiredUrl)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Direct access is not allowed");
        }
    }
}
