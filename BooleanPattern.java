public class BooleanPattern extends Pattern {

  private Boolean ifMissing;
  private String ifMissingMessage;

  public BooleanPattern(String name, String description) {
    super(name, description);
  }

  public BooleanPattern ifMissing(boolean ifMissing, String ifMissingMessage) {
    this.ifMissingMessage = ifMissingMessage;
    return ifMissing(ifMissing);
  }

  public BooleanPattern ifMissing(boolean ifMissing) {
    this.ifMissing = ifMissing;
    return this;
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return ifMissing == null 
        ? Result.EMPTY
        : new Result() {
          @Override public boolean getBoolean() { return ifMissing; }
        };
    } else {
      final boolean result = Boolean.getBoolean(sanitized);
      return new Result() {
        @Override public boolean isValid() { return true; }
        @Override public boolean getBoolean() { return result; }
      };
    }
  }

    @Override
  public String toString() {
    return super.toString() 
      + (ifMissing == null 
          ? ""
          : ", ifMissing=" + ifMissing);
  }
}