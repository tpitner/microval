public class IntPattern extends Pattern {

  private Integer ifMissing;
  private Integer ifInvalid;
  private String ifMissingMessage;
  private String ifInvalidMessage;

  private Integer min = null;
  private Integer max = null;

  public IntPattern(String name, String description) {
    super(name, description);
  }

  public IntPattern ifInvalid(int ifInvalid) {
    this.ifInvalid = ifInvalid;
    return this;
  }

  public IntPattern ifInvalid(int ifInvalid, String ifInvalidMessage) {
    this.ifInvalidMessage = ifInvalidMessage;
    return ifInvalid(ifInvalid);
  }

  public IntPattern ifMissing(int ifMissing) {
    this.ifMissing = ifMissing;
    return this;
  }

  public IntPattern ifMissing(int ifMissing, String ifMissingMessage) {
    this.ifMissingMessage = ifMissingMessage;
    return ifMissing(ifMissing);
  }

  public IntPattern min(int min) {
    this.min = min;
    return this;
  }

  public IntPattern max(int max) {
    this.max = max;
    return this;
  }

  public IntPattern range(int min, int max) {
    return min(min).max(max);
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return ifMissing == null 
        ? Result.EMPTY
        : new Result() {
          @Override public int getInt() { return ifMissing; }
          @Override public String getMessage() { return ifMissingMessage; }
        };
    } else {
      try {
        final int result = Integer.parseInt(sanitized);
        final boolean valid = 
          (min == null || min.intValue() <= result) 
            && (max == null || max.intValue() >= result);
        if(valid) return new Result() {
          @Override public boolean isValid() { return true; }
          @Override public int getInt() { return result; }
        };
      } catch(NumberFormatException nfe) {
      }
      return ifInvalid == null 
        ? Result.EMPTY
        : new Result() {
          @Override public String getMessage() { return ifInvalidMessage; }
          @Override public int getInt() { return ifInvalid; }
        };
    }
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