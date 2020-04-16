public class DoubleValue extends Value {

  private double doubleData;
  private double defaultData;
  private Double min = Double.NEGATIVE_INFINITY;
  private Double max = Double.POSITIVE_INFINITY;

  public DoubleValue(String name, String description) {
    super(name, description);
  }

  public DoubleValue defaultDouble(double defaultData) {
    this.defaultData = defaultData;
    return this;
  }

  public DoubleValue min(double min) {
    this.min = min;
    return this;
  }

  public DoubleValue max(double max) {
    this.max = max;
    return this;
  }

  public DoubleValue range(double min, double max) {
    return min(min).max(max);
  }

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }

  public double getDouble() {
    return valid() ? doubleData : defaultData;
  }

  @Override
  public boolean valid(String data) {
    if (super.valid(data)) {
      try {
        doubleData = Double.parseDouble(data());
        this.valid = min.doubleValue() <= doubleData && doubleData <= max.intValue();
      } catch (RuntimeException re) {
        this.valid = false;
      }
    } else
      this.valid = false;
    return valid;
  }

  @Override
  public String toString() {
    return "double(" + (valid() ? "" + getDouble() : "EMPTY") + ") " + name();
  }
}