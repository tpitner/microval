import java.nio.file.Path;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
  public static void main(String[] args) {
    Value[] pars = new Value[] {
      new BooleanValue("myBool", "myBool parameter")
        .defaultBoolean(true),
      new IntValue("myInt", "myInt parameter")
        .range(-10, 1000).defaultInt(100),
      new DoubleValue("myDouble", "myDouble parameter")
        .range(-123.4, 123.4).defaultDouble(100.0),
      new StringValue("myString", "myString parameter")
        .length(1, 10).defaultString("nnnnnnnn"),
      new RegexValue("myRegex", "myRegex parameter")
        .pattern("\\d*").defaultString("bbbbb"),
    };
    Arrays.asList(pars).forEach(v ->
      System.out.println(v.valid("100") + " " + v));
  }  
}