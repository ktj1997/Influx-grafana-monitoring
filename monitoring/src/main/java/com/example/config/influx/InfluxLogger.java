package com.example.config.influx;

import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfluxLogger {





  private final InfluxDBTemplate influxDBTemplate;





  public <T> void log(String measurement, T data) {

    Point p =
        Point.measurement(measurement)
            .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
            .addFieldsFromPOJO(data)
            .build();

    influxDBTemplate.write(p);
  }
}
