package com.example.config.influx;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.write.Point;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InfluxLogger {

  private final InfluxDBClient influxDBClient;

  public <T> void log(String measurement, Map<String, String> tags, Map<String, Object> fields) {
    Point point = Point.measurement(measurement);
    WriteApiBlocking writeApi = influxDBClient.getWriteApiBlocking();

    addTags(point, tags);
    addFields(point, fields);

    writeApi.writePoint(point);
  }

  private void addTags(Point point, Map<String, String> tags) {
    for (Map.Entry<String, String> tag : tags.entrySet()) {
      point.addTag(tag.getKey(), tag.getValue());
    }
  }

  private void addFields(Point point, Map<String, Object> fields) {
    for (Map.Entry<String, Object> field : fields.entrySet()) {
      Object value = field.getValue();

      if (value instanceof Number) {
        point.addField(field.getKey(), (Number) value);
      } else if (value instanceof Boolean) {
        point.addField(field.getKey(), (Boolean) value);
      } else {
        point.addField(field.getKey(), (String) value);
      }
    }
  }
}
