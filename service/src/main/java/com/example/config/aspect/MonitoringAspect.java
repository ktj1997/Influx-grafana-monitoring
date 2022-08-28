package com.example.config.aspect;

import com.example.config.kafka.KafkaTopicConstant;
import com.example.service.KafkaService;
import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class MonitoringAspect {

  private final KafkaService kafkaService;

  @Pointcut("@annotation(com.example.config.aspect.Monitoring)")
  private void MonitoringAnnotation() {

  }

  @Around("MonitoringAnnotation()")
  public void transferLogToInflux(ProceedingJoinPoint pjp) {
    try {
      MethodSignature signature = (MethodSignature) pjp.getSignature();
      Method method = signature.getMethod();

      Monitoring monitoring = method.getAnnotation(Monitoring.class);
      String topic = KafkaTopicConstant.get(monitoring.topic()).getTopic();

      Object response = pjp.proceed();

      kafkaService.send(topic, response);
    } catch (Throwable e) {
      log.error("Cannot Parse Key In Monitoring Annotation");
      e.printStackTrace();
    }
  }
}
