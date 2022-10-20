package com.example.service;

import com.example.config.influx.InfluxLogger;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

  private final InfluxLogger logger;

  public <T> void transferLogToInfluxDB(
      String measurement, Map<String, String> tags, Map<String, Object> fields) {
    logger.log(measurement, tags, fields);
  }
}
