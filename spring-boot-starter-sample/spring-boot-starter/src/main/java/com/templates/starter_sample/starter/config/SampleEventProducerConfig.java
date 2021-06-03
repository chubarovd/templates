package com.templates.starter_sample.starter.config;

import com.templates.starter_sample.starter.SampleProperties;
import com.templates.starter_sample.starter.service.SampleEventProducerService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableConfigurationProperties(SampleProperties.class)
public class SampleEventProducerConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = "sample.event-producer.enabled", havingValue = "true")
    public SampleEventProducerService sampleEventProducerService(
            ApplicationEventPublisher applicationEventPublisher, SampleProperties sampleProperties) {
        String message = sampleProperties.getEventProducer().getEventMessage();
        if(message == null) {
            message = "Sample default event message";
        }
        return new SampleEventProducerService(applicationEventPublisher, message);
    }
}
