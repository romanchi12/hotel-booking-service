package org.rodrigez.configuration;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().
                setPropertyCondition(Conditions.isNotNull()).
                setMatchingStrategy(MatchingStrategies.STANDARD).
                setFieldAccessLevel(AccessLevel.PRIVATE);
        return modelMapper;
    }
}
