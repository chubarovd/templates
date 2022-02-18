package com.springinaction.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component

@Aspect
public class InvocationLoggerAspect {

  private static final Logger log =
      LoggerFactory.getLogger("com.springinaction.aop.invocations-info");

  @Pointcut("@annotation(com.springinaction.aop.aspect.annotation.LogInvocation)"
      + " || @within(com.springinaction.aop.aspect.annotation.LogInvocation)")
  void invocationLogPC() {
  }

  @Around("invocationLogPC()")
  public Object logInvocationAfter(ProceedingJoinPoint joinPoint) throws Throwable {
    String invocationName = joinPoint.getSignature().toString();
    try {
      log.info("Before invocation: {}", invocationName);

      return joinPoint.proceed();

    } finally {
      log.info("After invocation: {}", invocationName);
    }
  }
}
