# microval
[![Run on Repl.it](https://repl.it/badge/github/tpitner/microval)](https://repl.it/github/tpitner/microval)

Ultra lightweight Java/Android validation framework 

## Description
- Library for conversion and validation 
- Extremely simple & easy to use
- Strongly typed: boolean, int, double, String, regex validated String, Path supported
- 

## Usage
- Create Patterns (either _BooleanPattern_, _IntPattern_, _DoublePattern_, _StringPattern_, _RegexPattern_, _PathPattern_) using constructors with Pattern _name_ and _description_ which is used to indicate errors in missing or invalid.
- Modify them by adding default values (_defaultIfMissing_, _defaultIfInvalid_).
- You may use patterns to _match_ String inputs, i.e. convert and validate them to resulting typed values. 
- The _match_ returns _Result_ which can be queried:
-- _isValid()_ true iff the _match(input)_ matched,
-- return the value read: eg. in case of _IntPattern_ _int getInt)_ returns the int read from input String,
-- _getMessage()_ returns error message in case the input was missing or not valid. 
- Validation is thread-safe (ie. one can validate against the same _Pattern_ in multiple threads concurrently).

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
