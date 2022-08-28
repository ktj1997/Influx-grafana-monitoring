package com.example.config.kafka;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum KafkaTopicConstant {
  EXAMPLE_SERVICE("example.service");


  private final String topic;
  private static final Map<String, KafkaTopicConstant> topics =
      Collections.unmodifiableMap(Stream.of(values())
          .collect(Collectors.toMap(KafkaTopicConstant::getTopic, Function.identity())));

  public static List<String> getTopics() {
    return topics.values().stream().map(KafkaTopicConstant::getTopic)
        .collect(Collectors.toList());
  }

  public static KafkaTopicConstant get(String key) {
    return Optional.ofNullable(topics.get(key)).orElseThrow(RuntimeException::new);
  }
}
