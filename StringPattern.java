public class StringValue extends Value {

  private String defaultData;
  private int minLength = -1;
  private int maxLength = -1;

  public StringValue(String name, String description) {
    super(name, description);
    this.sanitize = false;
  } 

  public StringValue defaultString(String defaultData) {
    this.defaultData = defaultData;
    return this;
  }

  public StringValue minLength(int minLength) {
    this.minLength = minLength;
    return this;
  }

  public StringValue maxLength(int maxLength) {
    this.maxLength = maxLength;
    return this;
  }

  public StringValue length(int minLength, int maxLength) {
    return minLength(minLength).maxLength(maxLength);
  }  

  @Override public String valid(String data) {
    this.valid = super.valid(data)
      && (minLength < 0 || minLength <= data().length()) 
      && (maxLength < 0 || data().length() <= maxLength);
    return valid;
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