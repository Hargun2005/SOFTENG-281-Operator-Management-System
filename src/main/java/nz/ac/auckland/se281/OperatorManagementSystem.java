package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private LocationTracker locationTrackerManager;
  private List<Operator> operators;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    locationTrackerManager = new LocationTracker(); // Initialize location trackers
    operators = new ArrayList<>();
  }

  public void searchOperators(String keyword) {
    // If the keyword is null or empty, display a message indicating no operators were found
    if (keyword == null || keyword.trim().isEmpty()) {
      MessageCli.OPERATORS_FOUND.printMessage("are", "no", "s", ".");
      return;
    }
    String renameKeyword = keyword.trim().toLowerCase();
    List<Operator> matchingOperators = new ArrayList<>();
    // Check if the keyword matches any location abbreviation
    String locationFullName = null;
    for (Location location : Location.values()) {
      if (location.getLocationAbbreviation().equalsIgnoreCase(renameKeyword)) {
        locationFullName = location.getFullName().toLowerCase();
        break;
      }
    }
    // Search for matching operators
    for (Operator operator : operators) {
      String operatorName = operator.getName().toLowerCase();
      String operatorLocation = operator.getLocation().toLowerCase();
      // Check if the keyword matches location abbreviation
      if (keyword.equals("*")
          || operatorName.contains(renameKeyword)
          || operatorLocation.contains(renameKeyword)
          || (locationFullName != null && operatorLocation.equals(locationFullName))) {
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
            operator.getName(), operator.getId(), operator.getLocation());
      }
    }
  }

  public void createOperator(String operatorName, String location) {
    // Trim thr operator name to remove spaces
    String trimName = operatorName.trim();
    // Validate that the operator name has at least 3 characters
    if (trimName.length() < 3) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_OPERATOR_NAME.printMessage(operatorName);
      return;
    }

    Location locationFound = Location.fromString(location);
    // Prints an error message if fromString returns null
    if (locationFound == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }
    String locationAsString = locationFound.getFullName();
    // For each loop to check the name and location of the operator that already exists
    for (Operator operator : operators) {
      if (operator.getName().equals(operatorName)
          && operator.getLocation().equals(locationAsString)) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, locationAsString);
        return;
      }
    }
    String locationAbbreviation = locationFound.getLocationAbbreviation();

    // Using First letter class to get the first letter of the operator name
    FirstLetters firstLetters = new FirstLetters();
    String operatorInitials = firstLetters.getFirstLetters(operatorName);

    // Find the tracker for the location and increment the count
    LocationTracker tracker = locationTrackerManager.findTracker(locationFound);
    // If the location tracker for the given location is not found, display an error message
    if (tracker == null) {
      MessageCli.OPERATOR_NOT_CREATED_INVALID_LOCATION.printMessage(location);
      return;
    }
    int operatorCount = tracker.incrementAndGetCount();
    // Generate a unique operator ID using the operator's initials, location abbreviation,
    // and a sequential number for the location
    String operatorId =
        operatorInitials + "-" + locationAbbreviation + "-" + String.format("%03d", operatorCount);
    // Add the new operator to the list
    operators.add(new Operator(operatorName, operatorId, locationAsString));
    // Print a success message
    MessageCli.OPERATOR_CREATED.printMessage(operatorName, operatorId, locationAsString);
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
