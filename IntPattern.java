public class IntValue extends Value {

  private int intData;
  private int defaultData;
  private Integer min = null;
  private Integer max = null;

  public IntValue(String name, String description) {
    super(name, description);
  }

  public IntValue defaultInt(int defaultData) {
    this.defaultData = defaultData;
    return this;
  }

  public IntValue min(int min) {
    this.min = min;
    return this;
  }

  public IntValue max(int max) {
    this.max = max;
    return this;
  }

  public IntValue range(int min, int max) {
    return min(min).max(max);
  }

  @Override
  public boolean valid(String data) {
    if (super.valid(data)) {
      try {
        intData = Integer.parseInt(data());
        this.valid = (min == null || min.intValue() <= intData) && (max == null || intData <= max.intValue());
      } catch (RuntimeException re) {
        this.valid = false;
      }
    } else
      this.valid = false;
    return valid;
  }

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }

  public int getInt() {
    return valid() ? intData : defaultData;
  }

  @Override
  public String toString() {
    return "int(" + (valid() ? "" + getInt() : "EMPTY") + ") " + name();
  }
}