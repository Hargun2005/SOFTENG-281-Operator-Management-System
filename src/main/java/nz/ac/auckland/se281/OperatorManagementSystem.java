package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;
import java.util.List;
public class OperatorManagementSystem {

  private List<LocationTracker> locationTrackers;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    locationTrackers = new ArrayList<>();
    for (Location location : Location.values()) {
      locationTrackers.add(new LocationTracker(location));
    }
  }

  public void searchOperators(String keyword) {
    MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
  }

  public void createOperator(String operatorName, String location) {
    Location locationFound = Location.fromString(location);
    String locationAsString = locationFound.getFullName();
    String locationAbbreviation = locationFound.getLocationAbbreviation();
    
    // Using First letter class to get the first letter of the operator name
    FirstLetters firstLetters = new FirstLetters();
    String operatorInitials = firstLetters.getFirstLetters(operatorName);

    // Find the tracker for the location and increment the count
    LocationTracker tracker = findLocationTracker(locationFound);
    int operatorCount = tracker.incrementAndGetCount();
    String operatorId = operatorInitials + "-" + locationAbbreviation + "-" + String.format("%03d", operatorCount);

    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId,locationAsString);
  }
  private LocationTracker findLocationTracker(Location location) {
    for (LocationTracker tracker : locationTrackers) {
      if (tracker.getLocation() == location) {
        return tracker; // Return the tracker if found
      }
    }
    return null; // Return null if no tracker is found
  }

  public void viewActivities(String operatorId) {
    // TODO implement
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // TODO implement
  }

  public void searchActivities(String keyword) {
    // TODO implement
  }

  public void addPublicReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addPrivateReview(String activityId, String[] options) {
    // TODO implement
  }

  public void addExpertReview(String activityId, String[] options) {
    // TODO implement
  }

  public void displayReviews(String activityId) {
    // TODO implement
  }

  public void endorseReview(String reviewId) {
    // TODO implement
  }

  public void resolveReview(String reviewId, String response) {
    // TODO implement
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // TODO implement
  }

  public void displayTopActivities() {
    // TODO implement
  }
}
