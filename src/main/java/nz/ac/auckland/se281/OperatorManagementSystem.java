package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;
import nz.ac.auckland.se281.Types.Location;

public class OperatorManagementSystem {

  private LocationTracker locationTrackerManager;
  private List<Operator> operators;
  private ActivityTracker activityTrackerManager;

  // Do not change the parameters of the constructor
  public OperatorManagementSystem() {
    locationTrackerManager = new LocationTracker(); // Initialize location trackers
    operators = new ArrayList<>();
    activityTrackerManager = new ActivityTracker(); // Initialize activity trackers
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
      if (operator.getName().equals(trimName) && operator.getLocation().equals(locationAsString)) {
        MessageCli.OPERATOR_NOT_CREATED_ALREADY_EXISTS_SAME_LOCATION.printMessage(
            operatorName, locationAsString);
        return;
      }
    }
    String locationAbbreviation = locationFound.getLocationAbbreviation();

    // Using First letter class to get the first letter of the operator name
    FirstLetters firstLetters = new FirstLetters();
    String operatorInitials = firstLetters.getFirstLetters(trimName);

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
    Operator newOperator = new Operator(trimName, operatorId, locationAsString);
    operators.add(newOperator); // Print a success message
    MessageCli.OPERATOR_CREATED.printMessage(trimName, operatorId, locationAsString);
    // Add a new ActivityTracker for the operator

    activityTrackerManager.addTracker(new ActivityTracker(newOperator));
  }

  public void viewActivities(String operatorId) {
    // Search for the operator by ID
    Operator operator = findOperatorById(operatorId);
    if (operator == null) {
      MessageCli.OPERATOR_NOT_FOUND.printMessage(operatorId);
      return;
    }
    // Get the list of activities for the operator
    List<Activity> activities = operator.getActivities();
    int activityCount = activities.size();
    if (activityCount == 0) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else {
      String verb;
      String plural;

      if (activityCount == 1) {
        verb = "is";
        plural = "y";
      } else {
        verb = "are";
        plural = "ies";
      }
      // Print the header
      MessageCli.ACTIVITIES_FOUND.printMessage(verb, String.valueOf(activityCount), plural, ":");
      // Print each activity's details using MessageCli.ACTIVITY_ENTRY
      for (Activity activity : activities) {
        MessageCli.ACTIVITY_ENTRY.printMessage(
            activity.getName(),
            activity.getActivityId(),
            activity.getType().toString(),
            operator.getName());
      }
    }
  }

  public void createActivity(String activityName, String activityType, String operatorId) {
    // Remove the leading and trailing spaces from the activity name
    String trimmedActivityName = activityName.trim();
    // Checks the length of the activity name
    if (activityName.trim().length() < 3) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_ACTIVITY_NAME.printMessage(activityName);
      return;
    }
    // Find the Operator by ID
    Operator operator = findOperatorById(operatorId);
    if (operator == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
    // Find the ActivityTracker for the operator
    ActivityTracker tracker = activityTrackerManager.findTracker(operator);
    if (tracker == null) {
      MessageCli.ACTIVITY_NOT_CREATED_INVALID_OPERATOR_ID.printMessage(operatorId);
      return;
    }
    // Generate the next activity ID
    String activityId = operator.generateNextActivityId();
    // Creates the activity
    Types.ActivityType activityTypeEnum = Types.ActivityType.fromString(activityType);
    Activity activity = new Activity(trimmedActivityName, activityTypeEnum, activityId);
    operator.addActivity(activity);
    // Print a success message
    MessageCli.ACTIVITY_CREATED.printMessage(
        trimmedActivityName, activityId, activityTypeEnum.toString(), operator.getName());
  }

  private Operator findOperatorById(String operatorId) {
    for (Operator operator : operators) {
      if (operator.getId().equals(operatorId)) {
        return operator;
      }
    }
    return null;
  }

  public void searchActivities(String keyword) {
    if (keyword == null || keyword.trim().isEmpty()) {
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
      return;
    }
    String searchKeyword = keyword.trim().toLowerCase();
    List<Activity> matchingActivities = new ArrayList<>();

    // Check if the keyword matches a location abbreviation
    String locationFullName = null;
    for (Location location : Location.values()) {
      if (location.getLocationAbbreviation().equalsIgnoreCase(searchKeyword)) {
        locationFullName = location.getFullName().toLowerCase();
        break;
      }
    }
    // Search for matching activities
    for (Operator operator : operators) {
      for (Activity activity : operator.getActivities()) {
        String activityName = activity.getName().toLowerCase();
        String activityType = activity.getType().toString().toLowerCase();
        String operatorLocation = operator.getLocation().toLowerCase();
        // Check if the keyword matches the activity name, ID, or type
        if (searchKeyword.equals("*")
            || activityName.contains(searchKeyword)
            || activityType.contains(searchKeyword)
            || operatorLocation.contains(searchKeyword)
            || (locationFullName != null && operatorLocation.equals(locationFullName))) {
          matchingActivities.add(activity);
        }
      }
    }
    // Determine the number of matching activities
    int count = matchingActivities.size();
    if (count == 0) {
      // No matching activities found
      MessageCli.ACTIVITIES_FOUND.printMessage("are", "no", "ies", ".");
    } else {
      // Determine verb and plural based on count
      String verb;
      String plural;
      if (count == 1) {
        verb = "is";
        plural = "y";
      } else {
        verb = "are";
        plural = "ies";
      }
      // Print the header
      MessageCli.ACTIVITIES_FOUND.printMessage(verb, String.valueOf(count), plural, ":");
      // Print the details of each matching activity
      for (Activity activity : matchingActivities) {
        // Extract the operator ID from the activity ID
        String operatorId =
            activity.getActivityId().split("-")[0]
                + "-"
                + activity.getActivityId().split("-")[1]
                + "-"
                + activity.getActivityId().split("-")[2];
        Operator operator = findOperatorById(operatorId);

        if (operator != null) {
          MessageCli.ACTIVITY_ENTRY.printMessage(
              activity.getName(),
              activity.getActivityId(),
              activity.getType().toString(),
              operator.getName());
        }
      }
    }
  }

  // Helper method to find activity by its ID
  private Activity findActivityById(String activityId) {
    for (Operator operator : operators) {
      for (Activity activity : operator.getActivities()) {
        if (activity.getActivityId().equals(activityId)) {
          return activity;
        }
      }
    }
    return null;
  }

  public void addPublicReview(String activityId, String[] options) {
    // Validate activity ID
    Activity activity = findActivityById(activityId);
    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }

    // Extract values from the options array
    String reviewerName = options[0];
    boolean isAnonymous = options[1].equalsIgnoreCase("y");
    int rating = Integer.parseInt(options[2]);
    String comments = options[3];

    // Adjust the rating using if-else statements
    if (rating < 1) {
      rating = 1; // Set to minimum valid rating
    } else if (rating > 5) {
      rating = 5; // Set to maximum valid rating
    }

    // Generate review ID
    int reviewNumber = activity.getReviews().size() + 1;
    String reviewId = activityId + "-R" + reviewNumber;

    // Create and add the public review
    PublicReview review = new PublicReview(reviewId, reviewerName, rating, comments, isAnonymous);
    activity.addReview(review);

    // Print success message
    MessageCli.REVIEW_ADDED.printMessage("Public", reviewId, activity.getName());
  }

  public void addPrivateReview(String activityId, String[] options) {
    // Validate activity ID
    Activity activity = findActivityById(activityId);
    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Extract values from the options array
    String reviewerName = options[0];
    String email = options[1];
    int rating = Math.max(1, Math.min(5, Integer.parseInt(options[2]))); // Adjust rating to 1-5
    String comments = options[3];
    boolean followUpRequested = options[4].equalsIgnoreCase("y");
    // Generate review ID
    int reviewNumber = activity.getReviews().size() + 1;
    String reviewId = activityId + "-R" + reviewNumber;
    // Create and add the private review
    PrivateReview review =
        new PrivateReview(reviewId, reviewerName, email, rating, comments, followUpRequested);
    activity.addReview(review);
    // Print success message
    MessageCli.REVIEW_ADDED.printMessage("Private", reviewId, activity.getName());
  }

  public void addExpertReview(String activityId, String[] options) {
    // Validate activity ID
    Activity activity = findActivityById(activityId);
    if (activity == null) {
      MessageCli.REVIEW_NOT_ADDED_INVALID_ACTIVITY_ID.printMessage(activityId);
      return;
    }
    // Extract Options
    String reviewName = options[0];
    int rating = Math.max(1, Math.min(5, Integer.parseInt(options[1]))); // Adjust rating to 1-5
    String comments = options[2];
    boolean recommended = options[3].equalsIgnoreCase("y");
    // Generate review ID
    int reviewNumber = activity.getReviews().size() + 1;
    String reviewId = activityId + "-R" + reviewNumber;
    // Create and add the expert review
    ExpertReview review = new ExpertReview(reviewId, reviewName, rating, comments, recommended);
    activity.addReview(review);
    // Print success message
    MessageCli.REVIEW_ADDED.printMessage("Expert", reviewId, activity.getName());
  }

  public void displayReviews(String activityId) {
    // Validate activity ID
    Activity activity = findActivityById(activityId);
    if (activity == null) {
      MessageCli.ACTIVITY_NOT_FOUND.printMessage(activityId);
      return;
    }
    // Get the list of reviews for the activity
    List<Review> reviews = activity.getReviews();

    // Check if there are no reviews
    if (reviews.isEmpty()) {
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", activity.getName());
      return;
    }
    int reviewCount = reviews.size();
    if (reviewCount == 0) {
      MessageCli.REVIEWS_FOUND.printMessage("are", "no", "s", ".");
    } else {
      String verb;
      String plural;
      if (reviewCount == 1) {
        verb = "is";
        plural = "";
      } else {
        verb = "are";
        plural = "s";
      }
      // Print the header
      MessageCli.REVIEWS_FOUND.printMessage(
          verb, String.valueOf(reviewCount), plural, activity.getName());
      // Print each review's details using MessageCli.REVIEW_ENTRY
      for (Review review : reviews) {
        review.display();
      }
    }
  }

  public void endorseReview(String reviewId) {
    // Find the review by its ID
    Review review = findReviewById(reviewId);
    if (review == null) {
      // If the review is not found, print an error message
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    // Check if the review is a PublicReview
    if (review instanceof PublicReview) {
      // Cast the review to PublicReview
      PublicReview publicReview = (PublicReview) review;
      publicReview.endorse(); // Endorse the review
      // Print success message
      MessageCli.REVIEW_ENDORSED.printMessage(reviewId);
    } else {
      // If the review is not a public review, print an error message
      MessageCli.REVIEW_NOT_ENDORSED.printMessage(reviewId);
    }
  }

  // Helper method to find a review by its ID
  private Review findReviewById(String reviewId) {
    for (Operator operator : operators) {
      for (Activity activity : operator.getActivities()) {
        for (Review review : activity.getReviews()) {
          if (review.getReviewId().equals(reviewId)) {
            return review;
          }
        }
      }
    }
    return null; // Return null if the review is not found
  }

  public void resolveReview(String reviewId, String response) {
    // Find the review by its ID
    Review review = findReviewById(reviewId);
    if (review == null) {
      // If the review is not found, print an error message
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    // Check if the review is a PrivateReview
    if (review instanceof PrivateReview) {
      // cast review to PrivateReview and resolve it
      PrivateReview privateReview = (PrivateReview) review;
      privateReview.setResponse(response);
      // Print success message
      MessageCli.REVIEW_RESOLVED.printMessage(reviewId);
    } else {
      // If the review is not a private review, print an error message
      MessageCli.REVIEW_NOT_RESOLVED.printMessage(reviewId);
    }
  }

  public void uploadReviewImage(String reviewId, String imageName) {
    // Find the review by its Id
    Review review = findReviewById(reviewId);
    if (review == null) {
      // if the review is not found, print an error message
      MessageCli.REVIEW_NOT_FOUND.printMessage(reviewId);
      return;
    }
    // Check if the review is an ExpertReview
    if (review instanceof ExpertReview) {
      // Cast the review to ExpertReview
      ExpertReview expertReview = (ExpertReview) review;
      expertReview.addImage(imageName); // Add the image to the review
      // Print success message
      MessageCli.REVIEW_IMAGE_ADDED.printMessage(imageName, reviewId);
    } else {
      // If the review is not an expert review, print an error message
      MessageCli.REVIEW_IMAGE_NOT_ADDED_NOT_EXPERT.printMessage(reviewId);
    }
  }

  public void displayTopActivities() {
    // iterate through all locations
    for (Location location : Location.values()) {
      String locationName = location.getFullName();
      List<Activity> activitiesInLocation = new ArrayList<>();

      // Collect all activities for operators in the current location
      for (Operator operator : operators) {
        if (operator.getLocation().equals(locationName)) {
          activitiesInLocation.addAll(operator.getActivities());
        }
      }
      // Find the top activity in the location
      Activity topActivity = null;
      double highestAverageRating = 0.0;

      for (Activity activity : activitiesInLocation) {
        List<Review> reviews = activity.getReviews();
        double totalRating = 0.0;
        int count = 0;
        // Calculate the average rating for public and expert reviews
        for (Review review : reviews) {
          if (review instanceof PublicReview || review instanceof ExpertReview) {
            totalRating += review.getRating();
            count++;
          }
        }
        if (count > 0) {
          double averageRating = totalRating / count;
          if (topActivity == null || averageRating > highestAverageRating) {
            topActivity = activity;
            highestAverageRating = averageRating;
          }
        }
      }
      // Print the result for the location
      if (topActivity == null) {
        MessageCli.NO_REVIEWED_ACTIVITIES.printMessage(locationName);
      } else {
        MessageCli.TOP_ACTIVITY.printMessage(
            locationName, topActivity.getName(), String.valueOf(highestAverageRating));
      }
    }
  }
}
