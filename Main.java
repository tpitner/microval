import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    var pars = Arrays.asList(
      new BooleanPattern("myBool", "myBool parameter").defaultIfMissing(true),
      new IntPattern("myInt", "myInt parameter")
        .range(-10, 1000).defaultIfMissing(0).defaultIfInvalid(-1),
      new DoublePattern("myDouble", "myDouble parameter")
        .range(-123.4, 123.4).defaultIfMissing(0.0).defaultIfInvalid(-1.0),
      new StringPattern("myString", "myString parameter")
        .length(1, 10).defaultIfMissing("none").defaultIfInvalid("invalid"),
      new RegexPattern("myRegex", "myRegex parameter")
        .pattern("\\d*").defaultIfMissing("none").defaultIfInvalid("invalid"),
      new PathPattern("myPath", "myPath parameter")
        .defaultIfMissing(Path.of("nonepath"))
        .defaultIfInvalid(Path.of("invalidpath"))
    );
    pars.forEach(v -> {
      Result result = v.match("x100");
      System.out.println(result.isValid() + " " + v);
    });
  }  
}