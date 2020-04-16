import java.nio.file.Path;

public class PathValue extends Value {

  private Path pathData;
  private Path defaultData;

  public PathValue(String name, Path defaultData, String description) {
    super(name, description);
    this.defaultData = defaultData;
  } 

  @Override public boolean valid() {
    try {
      pathData = Path.of(data());
      return pathData != null;
    } catch(RuntimeException re) {
      return false;
    }
  }

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }
  
  public Path getPath() {
    return valid() ? Path.of(data()) : defaultData;
  }

  @Override public String toString() {
    return "Path(" + (valid() ? String.valueOf(getPath()) : "EMPTY") + ") " + name();
  }
} 