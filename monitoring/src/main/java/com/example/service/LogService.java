package com.example.service;

import com.example.config.influx.InfluxLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogService {

  private final InfluxLogger logger;

  public <T> void transferLogToInfluxDB(String measurement, T log) {
    logger.log(measurement, log);
  }
}
