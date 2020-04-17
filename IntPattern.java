public class IntPattern extends Pattern {

  private Integer defaultIfMissing;
  private Integer defaultIfInvalid;

  private Integer min = null;
  private Integer max = null;

  public IntPattern(String name, String description) {
    super(name, description);
  }

  public IntPattern defaultIfInvalid(int defaultIfInvalid) {
    this.defaultIfInvalid = defaultIfInvalid;
    return this;
  }

  public IntPattern defaultIfMissing(int defaultIfMissing) {
    this.defaultIfMissing = defaultIfMissing;
    return this;
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
      return defaultIfMissing == null 
        ? new Result() { }
        : new Result() {
          @Override public int getInt() { return defaultIfMissing; }
        };
    } else {
      try {
        final int result = Integer.parseInt(sanitized);
        final boolean valid = 
          (min == null || min.intValue() <= result) 
            && (max == null || max.intValue() >= result);
        return valid ? new Result() {
          @Override public boolean isValid() { return true; }
          @Override public int getInt() { return result; }
        } : new Result() {
          @Override public int getInt() { return defaultIfInvalid; }
          @Override public String getMessage() { return description(); }
        };
      } catch(NumberFormatException nfe) {
        return defaultIfInvalid == null 
          ? new Result() {
            @Override public String getMessage() { return description(); }
          } : new Result() {
            @Override public String getMessage() { return description(); }
            @Override public int getInt() { return defaultIfInvalid; }
          };
      }
    }
  }
}