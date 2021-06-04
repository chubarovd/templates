package com.templates.camel_sample.route;

import com.templates.camel_sample.message.CamelMessage;
import com.templates.camel_sample.processor.SampleProcessor;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleRoute extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from("timer://sample?period=1000&repeatCount=5")
        .process(ex ->
            ex.getOut().setBody(
                new CamelMessage("Hello, it's " + UUID.randomUUID().toString())))
        .process(SampleProcessor.PROCESSOR_NAME)
        .end();
  }
}
