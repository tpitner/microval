public class BooleanParameter extends Parameter {

  private boolean booleanData;
  private boolean defaultData;

  public BooleanParameter(String name, String description) {
    super(name, description);
  } 

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }
  
  public boolean getBoolean() {
    return valid() ? booleanData : defaultData;
  }

  @Override public boolean valid() { 
    try {
      booleanData = Boolean.parseBoolean(data());
    } catch(RuntimeException re) {
      return false;
    }
    return true; 
  }

  @Override public String toString() {
    return "boolean(" + (valid() ? "" + getBoolean() : "EMPTY") + ") " + name();
  }
} 