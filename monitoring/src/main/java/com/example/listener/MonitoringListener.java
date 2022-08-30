package com.example.listener;

import com.example.config.influx.InfluxMeasurementsConstant;
import com.example.config.kafka.KafkaTopicConstant;
import com.example.model.ExampleModel;
import com.example.service.LogService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MonitoringListener {

  private final LogService logService;

  @KafkaListener(id = "monitoring-consumer", topics = KafkaTopicConstant.EXAMPLE)
  public void ConsumeServiceMonitoringRecord(
      ConsumerRecordMetadata consumerRecordMetadata,
      ConsumerRecord<String, String> consumerRecord
  ) {
    Gson gson = new Gson();

    String value = consumerRecord.value();
    ExampleModel model = gson.fromJson(value, ExampleModel.class);

    log.info("Consume {} from Topic {} ", model, consumerRecord.topic());

    logService.transferLogToInfluxDB(InfluxMeasurementsConstant.EXAMPLE_SERVICE, model);
  }
}
