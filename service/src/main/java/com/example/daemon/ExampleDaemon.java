package com.example.daemon;

import com.example.service.ExampleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExampleDaemon {

  private final ExampleService exampleService;

  @Scheduled(fixedDelay = 1L * 1000 * 60)
  public void scheduler1() {
    exampleService.run1();
  }

  @Scheduled(fixedDelay = 1L * 1000 * 60)
  public void scheduler2() {
    exampleService.run2();
  }
}
