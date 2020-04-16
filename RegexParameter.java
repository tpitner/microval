import java.util.regex.Pattern;

public class RegexParameter extends StringParameter {

  private Pattern pattern;

  public RegexParameter(String name, Pattern pattern, String description) {
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