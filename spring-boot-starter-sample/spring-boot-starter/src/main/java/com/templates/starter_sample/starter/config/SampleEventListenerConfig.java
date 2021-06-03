package com.templates.starter_sample.starter.config;

import com.templates.starter_sample.starter.listener.SampleEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleEventListenerConfig {

    @Bean
    public SampleEventListener sampleEventListener() {
        return new SampleEventListener();
    }
}
