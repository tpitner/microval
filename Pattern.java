public class Pattern implements Comparable<Pattern> {

  private String name;
  private String description;
  
  protected boolean sanitize = true;

  public Pattern(String name, String description) {
    requireNotNullBlank(name, "Name of Pattern must not be null or empty");
    requireNotNull(description, "Description of Pattern must not be null");
    this.name = name;
    this.description = description;
  }

  public Result match(String data) {
    return new Result() { };
  }
  
  public String doSanitize(String data) {
    return data == null ? null : (sanitize ? data.trim() : data);
  }

  public String name() {
    return name;
  }

  public String description() {
    return description;
  }

  public static void requireNotNull(Object s, String message) {
    if (s == null)
      throw new NullPointerException(message);
  }

  public static void requireNotNullBlank(String s, String message) {
    requireNotNull(s, message);
    if (s.isBlank())
      throw new IllegalArgumentException(message);
  }

  public static void requireNotNullEmpty(Object[] a, String message) {
    if (a == null)
      throw new NullPointerException(message);
    if (a.length == 0)
      throw new IllegalArgumentException(message);
  }

  public static void requireInRange(Object[] a, int index, String message) {
    if (index >= a.length)
      throw new ArrayIndexOutOfBoundsException(message);
  }

  @Override
  public int compareTo(Pattern o) {
    return name().compareTo(o.name());
  }

  @Override
  public boolean equals(Object o) {
    if (o != null && this.getClass() == o.getClass()) {
      return this.name().equals(((Pattern) o).name());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return name().hashCode();
  }

  @Override
  public String toString() {
    return this.getClass().getName() + " " + name;
  }
}