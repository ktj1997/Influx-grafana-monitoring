package com.example.config.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {

  @Bean
  public AdminClient adminClient(KafkaAdmin kafkaAdmin) {
    return AdminClient.create(kafkaAdmin.getConfigurationProperties());
  }

  @Bean
  public KafkaAdmin.NewTopics topics() {

    return new KafkaAdmin.NewTopics(
        KafkaTopicConstant.getTopics()
            .stream().map(topic ->
                TopicBuilder.name(topic).build()
            ).toArray(NewTopic[]::new)
    );
  }

}
