# microval
[![Run on Repl.it](https://repl.it/badge/github/tpitner/microval)](https://repl.it/github/tpitner/microval)

Ultra lightweight Java/Android validation framework 

## Description
- Library for conversion and validation 
- Extremely simple & easy to use
- Strongly typed: `boolean`, `int`, `double`, `String`, regex validated `String`, `Path` supported
- Allows provision of default values when input is missing (`null`) or invalid
- Allows to specify _min...max ranges_ for numeric types (`int`, `double`)
- Allows to specify _minimal and maximal length_ of `String` 
- Offers detailed diagnostics, distinguishes between missing on invalid inputs
- Is fully _thread-safe_

## Usage
- Create Patterns (either `BooleanPattern`, `IntPattern`, `DoublePattern`, `StringPattern`, `RegexPattern`, `PathPattern`) using constructors with Pattern `name` and `description` which is used to indicate errors in missing or invalid.
- Modify them by adding default values -- `ifMissing(valueIfMissing)`, `ifInvalid(valueIfInvalid)`.
- Overloaded methods may set also error messages -- `ifMissing(valueIfMissing, ifMissingMessage)`, `ifInvalid(valueIfInvalid, ifInvalidMessage)`.
- You may use patterns to `match` String inputs, i.e. convert and validate them to resulting typed values. 
- The `match` returns `Result` which can be queried, e.g.
- `isValid()` true iff the `match(input)` matched,
- The value converted and validated: eg. in case of `IntPattern` `int getInt()` returns the integer read from input `String`,
- Feedback: `getMessage()` returns error message in case the input was missing or not valid. 
- Validation is thread-safe (ie. one can validate against the same `Pattern` in multiple threads concurrently).

## Example

```java
Arrays.asList(
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
).forEach(v -> {
  Result result = v.match("x100");
  System.out.println(result.isValid() + " " + v);
});
```
