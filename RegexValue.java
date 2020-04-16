import java.util.regex.Pattern;

public class RegexValue extends StringValue {

  private Pattern pattern;

  public RegexValue(String name, String description) {
    super(name, description);
  }

  public RegexValue pattern(String pattern) {
    this.pattern = Pattern.compile(pattern);
    return this;
  }

  @Override public boolean valid(String data) {
    this.valid = super.valid(data) && pattern.matcher(data()).matches();
    return valid;
  }

  @Override public String toString() {
    return "RegexString(" + (valid() ? getString() : "EMPTY") + ") " + name();
  }
} 