package com.example.model;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ExampleModel {

  private String measurement;

  private Map<String, String> tags;

  private Map<String, Object> fields;
}
