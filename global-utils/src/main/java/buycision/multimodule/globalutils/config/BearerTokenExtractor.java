package buycision.multimodule.globalutils.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

public class BearerTokenExtractor {

    private static final String BEARER_PREFIX = "Bearer ";

    public static String extract(HttpServletRequest request) {
        String authorizationValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        return authorizationValue.replace(BEARER_PREFIX, "").strip();
    }
}
