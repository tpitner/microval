public abstract class Value implements Comparable<Parameter> {

  private String name;
  private String description;
  protected boolean sanitize = true;

  private String data;
  
  public Value(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public boolean doSanitize() {
    if(sanitize()) 
      if(data != null) {
        data = data.trim();
        return true;
      } else {
        return false;
      }
    else return true;
  }  
  
  public abstract boolean valid();
  protected abstract String defaultDataToString();

  protected String data() { return data; } 
  protected void data(String data) { this.data = data; } 
  public String name() { return name; }
  public String description() { return description; }
  public boolean sanitize() { return sanitize; }
  public void setSanitize(boolean sanitize) { this.sanitize = sanitize; }
  public void setSanitize() { this.sanitize = true; }

  public String usage() {
    return "[" + name() + "]\t default: '" + defaultDataToString() + "'\t" + description();
  }

  public static void requireNotNullBlank(String s, String message) {
    if(s == null) throw new NullPointerException(message);
    if(s.isBlank()) throw new IllegalArgumentException(message);
  }

  public static void requireNotNullEmpty(Object[] a, String message) {
    if(a == null) throw new NullPointerException(message);
    if(a.length == 0) throw new IllegalArgumentException(message);
  }

  public static void requireInRange(Object[] a, int index, String message) {
    if(index >= a.length) throw new ArrayIndexOutOfBoundsException(message);
  }

  @Override
  public int compareTo(Parameter o) {
    return name().compareTo(o.name());
  } 

  @Override
  public boolean equals(Object o) {
    if(o != null && this.getClass() == o.getClass()) {
      return this.name().equals(((Parameter)o).name());
    } 
    return false;
  }

  @Override
  public int hashCode() { return name().hashCode(); }
} 