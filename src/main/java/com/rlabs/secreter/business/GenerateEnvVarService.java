package com.rlabs.secreter.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rlabs.secreter.domain.Vars;
import java.util.HashMap;
import java.util.Map;

public class GenerateEnvVarService {
  private final ObjectMapper mapper = new ObjectMapper();

  public void generateOutput(Vars vars, Map<String, Object> secrets, String sufix)
      throws JsonProcessingException {
    Map<String, Object> output = new HashMap<>();

    vars.getPrivate().stream()
        .forEach(
            secret -> {
              secrets.entrySet().stream()
                  .filter(k -> k.getKey().equalsIgnoreCase(String.format("%s_%s", secret, sufix)))
                  .forEach(s -> output.put(secret, s.getValue()));
            });

    // Public data has high priority
    vars.getPublic().entrySet().stream()
        .forEach(
            k -> {
              output.put(k.getKey(), k.getValue());
            });

    System.out.println(
        String.format("::set-output name=env_output::%s", mapper.writeValueAsString(output)));
  }
}
