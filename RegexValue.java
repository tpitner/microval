import java.util.regex.Pattern;

public class RegexValue extends StringValue {

  private Pattern pattern;

  public RegexValue(String name, Pattern pattern, String description) {
    super(name, description);
    this.pattern = pattern;
  } 

  @Override public boolean valid() {
    return super.valid() && pattern.matcher(data()).matches();
  }

  @Override public String toString() {
    return "RegexString(" + (valid() ? getString() : "EMPTY") + ") " + name();
  }
} 