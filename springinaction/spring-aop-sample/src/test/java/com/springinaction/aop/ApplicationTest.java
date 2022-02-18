package com.springinaction.aop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.springinaction.aop.component.AnyBean;
import com.springinaction.aop.component.OneMoreBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTest {

  private static final String STRING = "HELLO, WORLD!";

  @Autowired
  private AnyBean anyBean;

  @Autowired
  private OneMoreBean oneMoreBean;

  @Test
  void testLogInvocations() {
    anyBean.invoke();

    oneMoreBean.foo();
    String actual = oneMoreBean.fee(STRING);
    assertEquals(STRING, actual);
  }

}
