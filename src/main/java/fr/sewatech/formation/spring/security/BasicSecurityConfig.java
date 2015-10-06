package fr.sewatech.formation.spring.security;//

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Profile(value = "basic")
public class BasicSecurityConfig extends CommonSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
            .authorizeRequests()
                .anyRequest().authenticated();
    }
}