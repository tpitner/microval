import java.nio.file.Path;

public class PathPattern extends Pattern {

  private Path defaultIfMissing;
  private Path defaultIfInvalid;

  public PathPattern(String name, String description) {
    super(name, description);
  }

  public PathPattern defaultIfMissing(Path defaultIfMissing) {
    this.defaultIfMissing = defaultIfMissing;
    return this;
  }

  public PathPattern defaultIfInvalid(Path defaultIfInvalid) {
    this.defaultIfInvalid = defaultIfInvalid;
    return this;
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return defaultIfMissing == null 
        ? new Result() { }
        : new Result() {
          @Override public Path getPath() { return defaultIfMissing; }
        };
    } else {
      try {
        final Path result = Path.of(sanitized);
        return new Result() {
          @Override public boolean isValid() { return true; }
          @Override public Path getPath() { return result; }
        };
      } catch(java.nio.file.InvalidPathException nfe) {
        return defaultIfInvalid == null 
          ? new Result() { 
            @Override public String getMessage() { return description(); }
          } : new Result() {
            @Override public Path getPath() { return defaultIfInvalid; }
          };
      }
    }
  }
}