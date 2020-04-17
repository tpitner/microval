public class DoublePattern extends Pattern {

  private Double ifMissing;
  private Double ifInvalid;
  private String ifMissingMessage;
  private String ifInvalidMessage;

  private Double min = Double.NEGATIVE_INFINITY;
  private Double max = Double.POSITIVE_INFINITY;

  public DoublePattern(String name, String description) {
    super(name, description);
  }

  public DoublePattern ifInvalid(double ifInvalid) {
    this.ifInvalid = ifInvalid;
    return this;
  }

  public DoublePattern ifInvalid(double ifInvalid, String ifInvalidMessage) {
    this.ifInvalidMessage = ifInvalidMessage;
    return ifInvalid(ifInvalid);
  }

  public DoublePattern ifMissing(double ifMissing) {
    this.ifMissing = ifMissing;
    return this;
  }

  public DoublePattern ifMissing(double ifMissing, String ifMissingMessage) {
    this.ifMissingMessage = ifMissingMessage;
    return ifMissing(ifMissing);
  }
    
  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return ifMissing == null 
        ? Result.EMPTY
        : new Result() {
          @Override public String getMessage() { return ifMissingMessage; }
          @Override public double getDouble() { return ifMissing; }
        };
    } else {
      try {
        final double result = Double.parseDouble(sanitized);
        final boolean valid = min.doubleValue() <= result && result <= max.intValue();
        if(valid) return new Result() {
          @Override public boolean isValid() { return true; }
          @Override public double getDouble() { return result; }
        };
      } catch(NumberFormatException nfe) {
      }
      return ifInvalid == null 
        ? Result.EMPTY
        : new Result() {
          @Override public String getMessage() { return ifInvalidMessage; }
          @Override public double getDouble() { return ifInvalid; }
        };
    }
  }

  public DoublePattern min(double min) {
    this.min = min;
    return this;
  }

  public DoublePattern max(double max) {
    this.max = max;
    return this;
  }

  public DoublePattern range(double min, double max) {
    return min(min).max(max);
  }

  @Override
  public String toString() {
    return super.toString() 
      + (min == null 
          ? ""
          : ", min=" + min)
      + (max == null 
          ? ""
          : ", max=" + max)
      + (ifMissing == null 
          ? ""
          : ", ifMissing=" + ifMissing)
      + (ifInvalid == null 
          ? ""
          : ", ifInvalid=" + ifInvalid)
      ;
  }
}