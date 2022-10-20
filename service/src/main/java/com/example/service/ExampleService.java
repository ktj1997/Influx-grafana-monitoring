package com.example.service;

import com.example.config.aspect.Monitoring;
import com.example.config.kafka.KafkaTopicConstant;
import com.example.model.ExampleModel;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

  @Monitoring(topic = KafkaTopicConstant.EXAMPLE_SERVICE)
  public ExampleModel run1() {
    int successCount = (int) (Math.random() * 100);
    int failCount = (int) (Math.random() * 100);

    return new ExampleModel(
        "measurement",
        Map.of("serviceType", "run2"),
        Map.of("success", successCount, "fail", failCount));
  }

  @Monitoring(topic = KafkaTopicConstant.EXAMPLE_SERVICE)
  public ExampleModel run2() {
    int successCount = (int) (Math.random() * 100);
    int failCount = (int) (Math.random() * 100);

    return new ExampleModel(
        "measurement",
        Map.of("serviceType", "run2"),
        Map.of("success", successCount, "fail", failCount));
  }
}
