package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Types.Location;

/**
 * The LocationTracker class is responsible for tracking operators at specific locations. It
 * maintains a count of operators for each location and provides methods to retrieve and manage
 * location-specific trackers.
 */
public class LocationTracker {

  private List<LocationTracker> locationTrackers;
  private Location location;
  private int operatorCount;

  // Constructor for individual LocationTracker
  public LocationTracker(Location location) {
    this.location = location;
    this.operatorCount = 0;
  }

  // Constructor to initialize the list of trackers
  public LocationTracker() {
    locationTrackers = new ArrayList<>();
    for (Location location : Location.values()) {
      locationTrackers.add(new LocationTracker(location));
    }
  }

  // Method to find a tracker for a specific location
  public LocationTracker findTracker(Location location) {
    for (LocationTracker tracker : locationTrackers) {
      if (tracker.getLocation() == location) {
        return tracker;
      }
    }
    return null;
  }

  // Getters and instance methods
  public Location getLocation() {
    return location;
  }

  public int incrementAndGetCount() {
    operatorCount++;
    return operatorCount;
  }

  public List<LocationTracker> getLocationTrackers() {
    return locationTrackers;
  }
}
