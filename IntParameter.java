public class IntParameter extends Parameter {

  private int intData;
  private int defaultData;
  private Integer min = null;
  private Integer max = null;

  public IntParameter(String name, String description) {
    super(name, description);
  } 

  @Override public boolean valid() {
    try {
      intData = Integer.parseInt(data());
      return (min == null || min.intValue() <= intData) 
          && (max == null || intData <= max.intValue());
    } catch(RuntimeException re) {
      return false;
    }
  }

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }
  
  public int getInt() {
    return valid() ? intData : defaultData;
  }

  @Override public String toString() {
    return "int(" + (valid() ? "" + getInt() : "EMPTY") + ") " + name();
  }
} 