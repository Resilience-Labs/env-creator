package com.rlabs.secreter.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rlabs.secreter.domain.Vars;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GenerateEnvVarService {
  private final ObjectMapper mapper = new ObjectMapper();

  public boolean isJSONValid(String jsonInString) {
    try {
      final ObjectMapper mapper = new ObjectMapper();
      mapper.readTree(jsonInString);
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public String generateOutput(Vars vars, Map<String, Object> secrets, String sufix)
      throws JsonProcessingException {
    Map<String, Object> output = new HashMap<>();

    if (vars.getPrivate() != null && secrets != null) {
      vars.getPrivate().stream()
          .forEach(
              secret -> {
                secrets.entrySet().stream()
                    .filter(k -> k.getKey().equalsIgnoreCase(String.format("%s_%s", secret, sufix)))
                    .forEach(s -> output.put(secret, s.getValue()));
              });
    }

    // Public data has high priority
    if (vars.getPublic() != null && !vars.getPublic().isEmpty()) {
      vars.getPublic().entrySet().stream()
          .forEach(
              k -> {
                output.put(k.getKey(), k.getValue());
              });
    }

    return mapper.writeValueAsString(output);
  }
}
