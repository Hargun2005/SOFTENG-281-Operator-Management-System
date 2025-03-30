package nz.ac.auckland.se281;

/**
 * The Operator class represents an operator with a name, unique ID, and location. It provides
 * methods to retrieve these attributes.
 */
public class Operator {
  private String name; // The name of the operator
  private String id; // The unique ID of the operator
  private String location; // The location of the operator

  // Constructs an Operator object with the specified name, ID, and location.
  public Operator(String name, String id, String location) {
    this.name = name;
    this.id = id;
    this.location = location;
  }

  // Returns the name of the operator.
  public String getName() {
    return name;
  }

  // Returns the unique ID of the operator.
  public String getId() {
    return id;
  }

  // Returns the location of the operator.
  public String getLocation() {
    return location;
  }
}
