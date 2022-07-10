package com.example.enum_validationapi.configuration;

import org.apache.el.stream.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;



@Configuration
@EnableJpaAuditing()
public class ProjectTaskConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {
        return null;
    }
}
