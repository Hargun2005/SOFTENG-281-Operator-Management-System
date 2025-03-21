package nz.ac.auckland.se281;

import nz.ac.auckland.se281.Types.Location;
import java.util.ArrayList;
import java.util.List;
public class OperatorManagementSystem {

  private List<LocationTracker> locationTrackers;
  private List<Operator> operators;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    locationTrackers = new ArrayList<>();
    operators = new ArrayList<>();
    for (Location location : Location.values()) {
      locationTrackers.add(new LocationTracker(location));
    }
  }

  public void searchOperators(String keyword) {
    List<Operator> matchingOperators = new ArrayList<>();
    String renameKeyword = keyword.toLowerCase();
   
    // Search for matching operators
    for (Operator operator : operators) {
      String operatorName = operator.getName().toLowerCase();
      String operatorLocation = operator.getLocation().toLowerCase();
      if (keyword.equals("*") || operatorName.contains(renameKeyword) || operatorLocation.contains(renameKeyword)) {
        matchingOperators.add(operator);
      }
       
    }

    // Determine the message based on the number of matching operators
    int count = matchingOperators.size();
    String verb;
    String plural;
    String punctuation;

    if (count == 0) {
        // No matching operators found
        MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
    } else {
        // Determine verb and plural based on count
        if (count == 1) {
            verb = "is";
            plural = "";
        } else {
            verb = "are";
            plural = "s";
        }
        punctuation = ":"; // Always use colon when there are matching operators
        MessageCli.OPERATORS_FOUND.printMessage(verb, String.valueOf(count), plural, punctuation);

        // Print each matching operator using MessageCli.OPERATOR_ENTRY
        for (Operator operator : matchingOperators) {
            MessageCli.OPERATOR_ENTRY.printMessage(
                operator.getName(), operator.getId(), operator.getLocation()
            );
        }
    }
}

  public void createOperator(String operatorName, String location) {
    Location locationFound = Location.fromString(location);
    String locationAsString = locationFound.getFullName();
    // For each loop to check the name and location of the operator that already exists
    for (Operator operator : operators) {
      if (operator.getName().equals(operatorName) && operator.getLocation().equals(locationAsString)) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(operatorName, locationAsString);
        return;
      }
    }
    String locationAbbreviation = locationFound.getLocationAbbreviation();
    
    // Using First letter class to get the first letter of the operator name
    FirstLetters firstLetters = new FirstLetters();
    String operatorInitials = firstLetters.getFirstLetters(operatorName);

    // Find the tracker for the location and increment the count
    LocationTracker tracker = findLocationTracker(locationFound);
    int operatorCount = tracker.incrementAndGetCount();
    String operatorId = operatorInitials + "-" + locationAbbreviation + "-" + String.format("%03d", operatorCount);

    operators.add(new Operator(operatorName, operatorId, locationAsString));

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
