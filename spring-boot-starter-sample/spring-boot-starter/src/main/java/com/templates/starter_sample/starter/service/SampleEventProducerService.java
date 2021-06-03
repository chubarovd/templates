package com.templates.starter_sample.starter.service;

import com.templates.starter_sample.starter.event.SampleEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
public class SampleEventProducerService {

    private final ApplicationEventPublisher eventPublisher;
    private final String message;

    @Scheduled(initialDelay = 5000, fixedDelay = 3000)
    public void generateEvent() {
        eventPublisher.publishEvent(new SampleEvent(this, message));
    }
}
