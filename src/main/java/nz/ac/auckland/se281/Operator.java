package nz.ac.auckland.se281;

public class Operator {
  private String name;
  private String id;
  private String location;

  public Operator(String name, String id, String location) {
    this.name = name;
    this.id = id;
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }

  public String getLocation() {
    return location;
  }
}
