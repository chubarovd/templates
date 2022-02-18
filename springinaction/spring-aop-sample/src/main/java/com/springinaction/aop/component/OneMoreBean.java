package com.springinaction.aop.component;

import com.springinaction.aop.aspect.annotation.LogInvocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component

@Slf4j
@LogInvocation
public class OneMoreBean {

  public void foo() {
    log.info("Hello from the outside!");
  }

  public <T> T fee(T obj) {
    log.info("Do something with: " + obj);
    return obj;
  }

}
