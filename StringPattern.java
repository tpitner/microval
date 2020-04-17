public class StringPattern extends Pattern {

  protected String ifMissing;
  protected String ifInvalid;
  protected String ifMissingMessage;
  protected String ifInvalidMessage;

  protected Integer minLength;
  protected Integer maxLength;

  public StringPattern(String name, String description) {
    super(name, description);
    this.sanitize = false;
  } 

  public StringPattern ifMissing(String ifMissing) {
    requireNotNullBlank(ifMissing, "ifMissing must not be null or empty");
    this.ifMissing = ifMissing;
    return this;
  }

  public StringPattern ifMissing(String ifMissing, String ifMissingMessage) {
    this.ifMissingMessage = ifMissingMessage;
    return ifMissing(ifMissing);
  }

  public StringPattern ifInvalid(String ifInvalid) {
    requireNotNullBlank(ifInvalid, "ifInvalid must not be null or empty");
    this.ifInvalid = ifInvalid;
    return this;
  }

  public StringPattern ifInvalid(String ifInvalid, String ifInvalidMessage) {
    this.ifInvalidMessage = ifInvalidMessage;
    return ifInvalid(ifInvalid);
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return ifMissing == null 
        ? Result.EMPTY
        : new Result() {
          @Override public String getString() { return ifMissing; }
        };
    } else {
        final boolean valid = 
          (minLength == null || minLength <= sanitized.length()) 
          && (maxLength == null || sanitized.length() <= maxLength);

        return valid 
        ? new Result() {
          @Override public boolean isValid() { return true; }
          @Override public String getString() { return sanitized; }
        } : (ifInvalid == null ? Result.EMPTY : new Result() {
            @Override public String getString() { return ifInvalid; }
            @Override public String getMessage() { return description(); }
        });
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

  @Override
  public String toString() {
    return super.toString() 
      + (minLength == null 
          ? ""
          : ", minLength=" + minLength)
      + (maxLength == null 
          ? ""
          : ", maxLength=" + maxLength)
      + (ifMissing == null 
          ? ""
          : ", ifMissing=" + ifMissing)
      + (ifInvalid == null 
          ? ""
          : ", ifInvalid=" + ifInvalid)
      ;
  }
} 