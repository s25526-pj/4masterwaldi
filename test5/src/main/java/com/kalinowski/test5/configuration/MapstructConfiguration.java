package com.kalinowski.test5.configuration;

import com.kalinowski.test5.mapper.EquationMapper;
import com.kalinowski.test5.mapper.EquationMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapstructConfiguration {

    @Bean
    public EquationMapper equationMapper() {
        return new EquationMapperImpl();
    }

}
