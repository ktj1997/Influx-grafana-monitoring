package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.influxdb.annotation.Column;

@Getter
@ToString
@AllArgsConstructor
public class ExampleModel {

  @Column(name = "successCount")
  private int successCount;

  @Column(name = "failCount")
  private int failCount;
}
