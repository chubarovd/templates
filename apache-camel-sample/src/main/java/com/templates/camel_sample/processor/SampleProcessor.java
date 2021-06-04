package com.templates.camel_sample.processor;

import com.templates.camel_sample.message.CamelMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleProcessor implements Processor {

  public static final String PROCESSOR_NAME = "sampleProcessor";

  @Override
  public void process(Exchange exchange) throws Exception {
    CamelMessage cm = exchange.getIn().getBody(CamelMessage.class);
    log.info("Receive:> " + cm.getContent());

    exchange.getOut().setHeaders(exchange.getIn().getHeaders());
    exchange.getOut().setBody(exchange.getIn().getBody());
  }
}
