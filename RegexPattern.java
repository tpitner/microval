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
      return defaultIfMissing == null 
        ? new Result() { }
        : new Result() {
            @Override public String getString() { return defaultIfMissing; }
        };
    } else {
      try {
        final boolean matches = pattern.matcher(sanitized).matches();
        return matches ? new Result() {
          @Override public boolean isValid() { return true; }
          @Override public String getString() { return sanitized; }
        } : new Result() {
          @Override public boolean isValid() { return false; }
          @Override public String getString() { return defaultIfInvalid; }
        };
      } catch(NumberFormatException nfe) {
        return defaultIfInvalid == null 
          ? new Result() {
            @Override public String getMessage() { return description(); }
          } : new Result() {
            @Override public String getString() { return defaultIfInvalid; }
            @Override public String getMessage() { return description(); }
          };
      }
    }
  }

  public RegexPattern pattern(String pattern) {
    this.pattern = Pattern.compile(pattern);
    return this;
  }
} 