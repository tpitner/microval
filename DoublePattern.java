public class DoublePattern extends Pattern {

  private Double defaultIfMissing;
  private Double defaultIfInvalid;

  private Double min = Double.NEGATIVE_INFINITY;
  private Double max = Double.POSITIVE_INFINITY;

  public DoublePattern(String name, String description) {
    super(name, description);
  }

  public DoublePattern defaultIfInvalid(double defaultIfInvalid) {
    this.defaultIfInvalid = defaultIfInvalid;
    return this;
  }

  public DoublePattern defaultIfMissing(double defaultIfMissing) {
    this.defaultIfMissing = defaultIfMissing;
    return this;
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return defaultIfMissing == null 
        ? new Result() { 
          @Override public String getMessage() { return description(); }
        }
        : new Result() {
          @Override public String getMessage() { return description(); }
          @Override public double getDouble() { return defaultIfMissing; }
        };
    } else {
      try {
        final double result = Double.parseDouble(sanitized);
        final boolean valid = min.doubleValue() <= result && result <= max.intValue();
        return valid ? new Result() {
          @Override public boolean isValid() { return true; }
          @Override public double getDouble() { return result; }
        } : new Result() {
          @Override public double getDouble() { return defaultIfInvalid; }
          @Override public String getMessage() { return description(); }
        };
      } catch(NumberFormatException nfe) {
        return defaultIfInvalid == null 
        ? new Result() {
          @Override public String getMessage() { return description(); }
        } : new Result() {
          @Override public String getMessage() { return description(); }
          @Override public double getDouble() { return defaultIfInvalid; }
        };
      }
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
}