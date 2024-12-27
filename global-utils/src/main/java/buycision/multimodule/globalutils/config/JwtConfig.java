package buycision.multimodule.globalutils.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Component;

@Getter
@Component
@ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private long expireTime;
}
