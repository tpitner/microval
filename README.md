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
- Offers detailed diagnostics on missing on invalid inputs (separated messages will be added in future)
- Is fully _thread-safe_

## Usage
- Create Patterns (either `BooleanPattern`, `IntPattern`, `DoublePattern`, `StringPattern`, `RegexPattern`, `PathPattern`) using constructors with Pattern `name` and `description` which is used to indicate errors in missing or invalid.
- Modify them by adding default values (`defaultIfMissing`, `defaultIfInvalid`).
- You may use patterns to `match` String inputs, i.e. convert and validate them to resulting typed values. 
- The `match` returns `Result` which can be queried:
-- `isValid()` true iff the `match(input)` matched,
-- return the value read: eg. in case of `IntPattern` `int getInt()` returns the integer read from input `String`,
-- `getMessage()` returns error message in case the input was missing or not valid. 
- Validation is thread-safe (ie. one can validate against the same `Pattern` in multiple threads concurrently).

## Example

```java
Arrays.asList(
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
).forEach(v -> {
  Result result = v.match("x100");
  System.out.println(result.isValid() + " " + v);
});
```
