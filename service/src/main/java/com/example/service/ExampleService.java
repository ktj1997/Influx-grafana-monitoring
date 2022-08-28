package com.example.service;

import com.example.config.aspect.Monitoring;
import com.example.model.ExampleModel;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {
  @Monitoring(topic = "example.service")
  public ExampleModel run() {
    int successCount = (int) (Math.random() * 100);
    int failCount = (int) (Math.random() * 100);

    return new ExampleModel(
        successCount,
        failCount
    );
  }

}
