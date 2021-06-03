package com.templates.starter_sample.starter.listener;

import com.templates.starter_sample.starter.event.SampleEvent;
import org.springframework.context.ApplicationListener;

public class SampleEventListener implements ApplicationListener<SampleEvent> {

    public void onApplicationEvent(SampleEvent sampleEvent) {
        System.out.println("SampleEventListener:> got event message: " + sampleEvent.getMessage());
    }
}
