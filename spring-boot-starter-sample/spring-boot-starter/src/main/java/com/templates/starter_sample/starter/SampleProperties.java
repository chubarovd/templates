package com.templates.starter_sample.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("sample")
public class SampleProperties {

    private EventProducer eventProducer;

    @Data
    public static class EventProducer {
        private boolean enabled;
        private String eventMessage;
    }
}
