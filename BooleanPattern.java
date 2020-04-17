public class BooleanPattern extends Pattern {

  private Boolean defaultIfMissing;

  public BooleanPattern(String name, String description) {
    super(name, description);
  }

  public BooleanPattern defaultIfMissing(boolean defaultIfMissing) {
    this.defaultIfMissing = defaultIfMissing;
    return this;
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return defaultIfMissing == null 
        ? new Result(){ }
        : new Result(){
          @Override public boolean getBoolean() { return defaultIfMissing; }
        };
    } else {
      final boolean result = Boolean.getBoolean(sanitized);
      return new Result() {
        @Override public boolean isValid() { return true; }
        @Override public boolean getBoolean() { return result; }
      };
    }
  }
}