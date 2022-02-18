package com.springinaction.aop.component;

import com.springinaction.aop.aspect.annotation.LogInvocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component

@Slf4j
public class AnyBean {

  @LogInvocation
  public void invoke() {
    log.info("Hello, world!");
  }
}
