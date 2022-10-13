package com.example.config.kafka;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

@Configuration
public class KafkaConsumerConfig {

  @Value("${kafka.server}")
  private String BROKER_URL;

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String>
      concurrentKafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
    ConcurrentKafkaListenerContainerFactory<String, String> containerFactory =
        new ConcurrentKafkaListenerContainerFactory<>();

    containerFactory.setConsumerFactory(consumerFactory);
    return containerFactory;
  }

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> properties = new LinkedHashMap<>();

    properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_URL);
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

    return new DefaultKafkaConsumerFactory<>(properties);
  }
}
