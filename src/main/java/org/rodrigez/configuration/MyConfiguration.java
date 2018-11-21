package org.rodrigez.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Configuration
public class MyConfiguration {
    @Bean
    public SimpleDateFormat dateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        TimeZone timeZone = TimeZone.getTimeZone("Europe/Kiev");
        dateFormat.setTimeZone(timeZone);
        return dateFormat;
    }
}