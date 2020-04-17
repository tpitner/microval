import java.util.regex.Pattern;

public class RegexPattern extends StringPattern {

  private Pattern pattern;

  public RegexPattern(String name, String description) {
    super(name, description);
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
      try {
        final boolean matches = pattern.matcher(sanitized).matches();
        if (matches) return new Result() {
          @Override public boolean isValid() { return true; }
          @Override public String getString() { return sanitized; }
        };
      } catch(NumberFormatException nfe) {
      }
      return ifInvalid == null ? Result.EMPTY : new Result() {
        @Override public String getString() { return ifInvalid; }
        @Override public String getMessage() { return description(); }
      };
    }
  }

  public RegexPattern pattern(String pattern) {
    requireNotNullBlank(pattern, "Regex pattern must not be null or empty");
    this.pattern = Pattern.compile(pattern);
    return this;
  }

  @Override
  public String toString() {
    return super.toString() 
      + (pattern == null 
          ? ""
          : ", pattern=" + pattern);
  }
} 