import java.nio.file.Path;

public class Result {

  public boolean isValid() { return false; }

  public String getMessage() { return "Cannot parse null String"; }

  public boolean getBoolean() {
    throw new UnsupportedOperationException();
  }

  public int getInt() {
    throw new UnsupportedOperationException();
  }

  public double getDouble() {
    throw new UnsupportedOperationException();
  }

  public String getString() {
    throw new UnsupportedOperationException();
  }

  public Path getPath() {
    throw new UnsupportedOperationException();
  }
}