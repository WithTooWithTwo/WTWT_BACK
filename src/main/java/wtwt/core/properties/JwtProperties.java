package wtwt.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public record JwtProperties(
    String issuer,
    String secretKey,
    int expiredAfter,
    int refreshExpiredAfter
) {

}
