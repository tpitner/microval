public class StringValue extends Value {

  private String defaultData;
  private int minLength = -1;
  private int maxLength = -1;

  public StringValue(String name, String description) {
    super(name, description);
    this.sanitize = false;
  } 

  @Override public boolean valid() {
    return (data() != null) 
      && (minLength < 0 || minLength <= data().length()) 
      && (maxLength < 0 || data().length() <= maxLength);
  }

  public String defaultDataToString() {
    return defaultData;
  }
  
  public String getString() {
    return valid() ? data() : defaultData;
  }

  @Override public String toString() {
    return "String(" + (valid() ? getString() : "EMPTY") + ") " + name();
  }
} 