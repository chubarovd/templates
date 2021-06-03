package com.templates.starter_sample.starter.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class SampleEvent extends ApplicationEvent {

    private String message;

    public SampleEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
