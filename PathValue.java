import java.nio.file.Path;

public class PathValue extends Value {

  private Path pathData;
  private Path defaultData;

  public PathValue(String name, String description) {
    super(name, description);
  }

  public PathValue defaultData(Path defaultData) {
    this.defaultData = defaultData;
    return this;
  }

  @Override
  public boolean valid(String data) {
    if (super.valid(data)) {
      try {
        pathData = Path.of(data());
        this.valid = pathData != null;
      } catch (RuntimeException re) {
        this.valid = false;
      }
    } else
      this.valid = false;
    return valid;
  }

  public String defaultDataToString() {
    return String.valueOf(defaultData);
  }

  public Path getPath() {
    return valid() ? Path.of(data()) : defaultData;
  }

  @Override
  public String toString() {
    return "Path(" + (valid() ? String.valueOf(getPath()) : "EMPTY") + ") " + name();
  }
}