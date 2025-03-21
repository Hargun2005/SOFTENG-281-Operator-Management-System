package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;

public class LocationTracker {
 
  private Location location;
  private int operatorCount;

  public LocationTracker(Location location){
    this.location = location;
    this.operatorCount = 0;
  }
  public Location getLocation() {
    return location;
  }

  public int incrementAndGetCount() {
    operatorCount++;
    return operatorCount;
  }
  


}
