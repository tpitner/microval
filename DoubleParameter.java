public class DoubleParameter extends Parameter {

  private double doubleData;
  private double defaultData;
  private Double min = Double.NEGATIVE_INFINITY;
  private Double max = Double.POSITIVE_INFINITY;
  
  public DoubleParameter(String name, String description) {
    super(name, description);
  } 

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }
  
  public double getDouble() {
    return valid() ? doubleData : defaultData;
  }

  @Override public boolean valid() { 
    try {
      doubleData = Double.parseDouble(data());
    } catch(RuntimeException re) {
      return false;
    }
    return true; 
  }

  @Override public String toString() {
    return "double(" + (valid() ? "" + getDouble() : "EMPTY") + ") " + name();
  }
} 