package fr.sewatech.formation.spring.security;//

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

@Configuration
@Profile(value = "encoded")
public class AdvancedDigestSecurityConfig extends CommonSecurityConfig {

    private static final String REALM_NAME = "example";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        DigestAuthenticationEntryPoint authenticationEntryPoint = new DigestAuthenticationEntryPoint();
        authenticationEntryPoint.setKey("sewatech");
        authenticationEntryPoint.setRealmName(REALM_NAME);

        DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
        filter.setAuthenticationEntryPoint(authenticationEntryPoint);
        filter.setUserDetailsService(userDetailsService());
        filter.setPasswordAlreadyEncoded(true);

        http.csrf().disable()
            .addFilter(filter)
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and()
            .authorizeRequests()
                .anyRequest().authenticated();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        Properties users = new Properties();
        users.setProperty("user", digest("user", "pwd") + ",USER");
        users.setProperty("admin", digest("admin", "admin") + ",USER,ADMIN");
        return new InMemoryUserDetailsManager(users);
    }

    private String digest(String username, String password) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }

        String data = username + ":" + REALM_NAME + ":" + password;
        return new String(Hex.encode(digest.digest(data.getBytes())));
    }
}