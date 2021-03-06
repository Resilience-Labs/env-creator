/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.rlabs.secreter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rlabs.secreter.business.GenerateEnvVarService;
import com.rlabs.secreter.domain.Vars;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Map;

public class App {
  private static final ObjectMapper mapper = new ObjectMapper();

  public static void main(String[] args) {

    GenerateEnvVarService service = new GenerateEnvVarService();

    System.out.println("INPUT");
    System.out.println("VARS: " + System.getenv("VARS"));
    System.out.println("SECRETS: " + System.getenv("SECRETS"));
    System.out.println("SUFIX: " + System.getenv("SUFIX"));

    try {

      if (System.getenv("VARS") == null) {
        System.out.println("WARNING: VARS empty");
      } else {
        if (!service.isJSONValid(System.getenv("VARS"))) {
          System.out.println("ERROR: VARS is invilid");
          throw new RuntimeException("Invalid JSON for VARS");
        }
      }
      if (System.getenv("SECRETS") == null) {
        System.out.println("WARNING: SECRETS empty");
      } else {
        if (!service.isJSONValid(System.getenv("SECRETS"))) {
          System.out.println("ERROR: SECRETS is invilid");
          throw new RuntimeException("Invalid JSON for SECRETS");
        }
      }
      if (System.getenv("SUFIX") == null) {
        System.out.println("WARNING: SUFIX empty");
      }

      Vars vars = mapper.readValue(System.getenv("VARS"), Vars.class);
      Map<String, Object> secrets = mapper.readValue(System.getenv("SECRETS"), Map.class);
      String sufix = System.getenv("SUFIX");

      String output = service.generateOutput(vars, secrets, sufix);

      System.out.println("OUTPUT" + output);
      System.out.println(String.format("::set-output name=env-output::%s", output));

      // Write to file
      try (PrintStream out = new PrintStream(new FileOutputStream("output"))) {
        // String encoded =
        // Base64.getEncoder().encodeToString(output.getBytes(StandardCharsets.UTF_8));

        out.print(output);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        throw new RuntimeException(e.getMessage(), e);
      }
    } catch (JsonMappingException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage(), e);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage(), e);
    }
  }
}
