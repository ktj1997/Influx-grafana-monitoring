package com.example.service;

import com.example.config.aspect.Monitoring;
import com.example.config.kafka.KafkaTopicConstant;
import com.example.model.ExampleModel;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

  @Monitoring(topic = KafkaTopicConstant.EXAMPLE_SERVICE)
  public ExampleModel run() {
    int successCount = (int) (Math.random() * 100);
    int failCount = (int) (Math.random() * 100);

    return new ExampleModel(
        successCount,
        failCount
    );
  }

}
