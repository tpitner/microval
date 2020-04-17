public class StringPattern extends Pattern {

  protected String defaultIfMissing;
  protected String defaultIfInvalid;

  private int minLength = -1;
  private int maxLength = -1;

  public StringPattern(String name, String description) {
    super(name, description);
    this.sanitize = false;
  } 

  public StringPattern defaultIfMissing(String defaultIfMissing) {
    this.defaultIfMissing = defaultIfMissing;
    return this;
  }

  public StringPattern defaultIfInvalid(String defaultIfInvalid) {
    this.defaultIfInvalid = defaultIfInvalid;
    return this;
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return defaultIfMissing == null 
        ? new Result() {}
        : new Result() {
          @Override public String getString() { return defaultIfMissing; }
        };
    } else {
      try {
        final boolean valid = 
          (minLength < 0 || minLength <= sanitized.length()) 
          && (maxLength < 0 || sanitized.length() <= maxLength);
        return valid ? new Result() {
          @Override public boolean isValid() { return true; }
          @Override public String getString() { return sanitized; }
        } : new Result() {
          @Override public String getString() { return defaultIfInvalid; }
        };
      } catch(NumberFormatException nfe) {
        return defaultIfInvalid == null 
          ? new Result() {
            @Override public String getMessage() { return description(); }
          } : new Result() {
            @Override public String getString() { return defaultIfInvalid; }
            @Override public String getMessage() { return description(); }
          };
      }
    }
  }

  public StringPattern minLength(int minLength) {
    this.minLength = minLength;
    return this;
  }

  public StringPattern maxLength(int maxLength) {
    this.maxLength = maxLength;
    return this;
  }

  public StringPattern length(int minLength, int maxLength) {
    return minLength(minLength).maxLength(maxLength);
  }  
} 