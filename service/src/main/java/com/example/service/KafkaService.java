package com.example.service;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaService {

  private final KafkaTemplate kafkaTemplate;

  public <T> void send(String topic, T record) {
    Gson gson = new Gson();
    String json = gson.toJson(record);

    ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topic, json);
    sendResult.addCallback(new KafkaSendCallback<String, String>() {

      @Override
      public void onSuccess(SendResult<String, String> result) {
        log.info("Send {} to Topic {} Success", record, topic);
      }

      @Override
      public void onFailure(KafkaProducerException ex) {
        log.error("Error Occurred at Record :{}", ex.getFailedProducerRecord());
      }
    });
  }
}
