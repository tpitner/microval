import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    var pars = Arrays.asList(

      new BooleanPattern("myBool", "myBool parameter")
      .ifMissing(true, "You could provide boolean"),

      new IntPattern("myInt", "myInt parameter")
        .range(-10, 1000)
        .ifMissing(0, "You could provide int")
        .ifInvalid(-1, "You should provide int but provided something else"),

      new DoublePattern("myDouble", "myDouble parameter")
        .range(-123.4, 123.4)
        .ifMissing(0.0, "You could provide double")
        .ifInvalid(-1.0, "You should provide provide double but..."),

      new StringPattern("myString", "myString parameter")
        .length(1, 10).ifMissing("none").ifInvalid("invalid"),
        
      new RegexPattern("myRegex", "myRegex parameter")
        .pattern("\\d*").ifMissing("none").ifInvalid("invalid"),
      new PathPattern("myPath", "myPath parameter")
        .ifMissing(Path.of("nonepath"))
        .ifInvalid(Path.of("invalidpath"))
    );

    System.out.println("Matching '100'");
    pars.forEach(v -> {
      Result result = v.match("100");
      System.out.println(result.isValid() + " " + v);
    });

    System.out.println("\nMatching 'bbb'");
    pars.forEach(v -> {
      Result result = v.match("bbb");
      System.out.println(result.isValid() + " " + v);
    });
    
    System.out.println("\nMatching ''");
    pars.forEach(v -> {
      Result result = v.match("");
      System.out.println(result.isValid() + " " + v);
    });
    
    System.out.println("\nMatching null");
    pars.forEach(v -> {
      Result result = v.match(null);
      System.out.println(result.isValid() + " " + v);
    });
  }  
}