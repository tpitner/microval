import java.nio.file.Path;

public class PathPattern extends Pattern {

  private Path ifMissing;
  private Path ifInvalid;
  private String ifMissingMessage;
  private String ifInvalidMessage;

  public PathPattern(String name, String description) {
    super(name, description);
  }

  public PathPattern ifMissing(Path ifMissing) {
    requireNotNull(ifMissing, "ifMissing must not be null");
    this.ifMissing = ifMissing;
    return this;
  }

  public PathPattern ifMissing(Path ifMissing, String ifMissingMessage) {
    this.ifMissingMessage = ifMissingMessage;
    return ifMissing(ifMissing);
  }

  public PathPattern ifInvalid(Path ifInvalid) {
    requireNotNull(ifMissing, "ifInvalid must not be null");
    this.ifInvalid = ifInvalid;
    return this;
  }

  public PathPattern ifInvalid(Path ifInvalid, String ifInvalidMessage) {
    this.ifInvalidMessage = ifInvalidMessage;
    return ifInvalid(ifInvalid);
  }

  @Override
  public Result match(String data) {
    final String sanitized = doSanitize(data);
    if(sanitized == null) {
      return ifMissing == null 
        ? Result.EMPTY
        : new Result() {
          @Override public Path getPath() { return ifMissing; }
          @Override public String getMessage() { return ifMissingMessage; }
        };
    } else {
      try {
        final Path result = Path.of(sanitized);
        return new Result() {
          @Override public boolean isValid() { return true; }
          @Override public Path getPath() { return result; }
        };
      } catch(java.nio.file.InvalidPathException nfe) {
      }
      return ifInvalid == null ? Result.EMPTY : new Result() {
        @Override public Path getPath() { return ifInvalid; }
      };
    }
  }

  @Override
  public String toString() {
    return super.toString() 
      + (ifMissing == null 
          ? ""
          : ", ifMissing=" + ifMissing)
      + (ifInvalid == null 
          ? ""
          : ", ifInvalid=" + ifInvalid)
      ;
  }
}