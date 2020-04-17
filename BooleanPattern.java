public class BooleanValue extends Value {

  private boolean booleanData;
  private boolean defaultData;

  public BooleanValue(String name, String description) {
    super(name, description);
  }

  public BooleanValue defaultBoolean(boolean defaultData) {
    this.defaultData = defaultData;
    return this;
  }

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }

  public boolean getBoolean() {
    return valid() ? booleanData : defaultData;
  }

  @Override
  public boolean valid(String data) {
    if (super.valid(data)) {
      try {
        booleanData = Boolean.getBoolean(data());
      } catch (RuntimeException re) {
        this.valid = false;
      }
      this.valid = true;
    } else
      this.valid = false;
    return valid;
  }

  @Override
  public String toString() {
    return "boolean(" + (valid() ? "" + getBoolean() : "EMPTY") + ") " + name();
  }
}