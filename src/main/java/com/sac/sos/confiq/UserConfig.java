package com.sac.sos.confiq;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public BasicPasswordEncryptor jasyptPasswordEncoder() {
        return new BasicPasswordEncryptor();
    }
}
