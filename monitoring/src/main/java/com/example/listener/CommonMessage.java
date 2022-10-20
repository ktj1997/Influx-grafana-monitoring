package com.example.listener;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommonMessage {

  private String measurement;
  private Map<String, String> tags;
  private Map<String, Object> fields;
}
