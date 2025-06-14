package com.project.auth_sys.config;


import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class JwtKeyLoader {

  @Value("${jwt.private.key-location}")
  private Resource privateKeyResource;

  @Value("${jwt.public.key-location}")
  private Resource publicKeyResource;

  @Bean
  public RSAPrivateKey rsaPrivateKey() throws Exception {
    String key = loadKey(privateKeyResource, "PRIVATE");
    byte[] decoded = Base64.getDecoder().decode(key);
    PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decoded);
    return (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(spec);
  }

  @Bean
  public RSAPublicKey rsaPublicKey() throws Exception {
    String key = loadKey(publicKeyResource, "PUBLIC");
    byte[] decoded = Base64.getDecoder().decode(key);
    X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
    return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(spec);
  }

  private String loadKey(Resource resource, String type) throws IOException {
    try (InputStream is = resource.getInputStream()) {
      return new String(is.readAllBytes())
          .replace("-----BEGIN " + type + " KEY-----", "")
          .replace("-----END " + type + " KEY-----", "")
          .replaceAll("\\s", "");
    }
  }
}
