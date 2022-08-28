package com.example.config.kafka;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.protocol.types.Field.Str;
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
        Arrays.stream(KafkaTopicConstant.class.getFields())
            .filter(it -> {
                  int modifier = it.getModifiers();
                  return
                      Modifier.isFinal(modifier)
                          && Modifier.isStatic(modifier)
                          && it.getType() == String.class;
                }
            ).map(topic -> {
              try {
                return TopicBuilder.name((String) topic.get(null)).build();
              } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
              }
            }).toArray(NewTopic[]::new));
  }
}
