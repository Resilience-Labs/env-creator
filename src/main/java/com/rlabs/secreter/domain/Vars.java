package com.rlabs.secreter.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Vars {
  @JsonProperty("public")
  private Map<String, Object> _public;

  @JsonProperty("private")
  private List<String> _private;

  public Map<String, Object> getPublic() {
    return _public;
  }

  public void setPublic(Map<String, Object> _public) {
    this._public = _public;
  }

  public List<String> getPrivate() {
    return _private;
  }

  public void setPrivate(List<String> _private) {
    this._private = _private;
  }

  public Vars() {}

  public Vars(Map<String, Object> _public, List<String> _private) {
    this._public = _public;
    this._private = _private;
  }
}
