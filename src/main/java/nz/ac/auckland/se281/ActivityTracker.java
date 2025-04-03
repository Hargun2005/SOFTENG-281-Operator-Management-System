package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

public class ActivityTracker {
  private List<ActivityTracker> activityTracker; // List of activities associated with the operator
  private Operator operator; // The operator associated with this activity tracker
  private int activityCount; // The count of activities for the operator

  // Constructor for individual ActivityTracker
  public ActivityTracker(Operator operator) {
    this.operator = operator;
    this.activityCount = 0;
  }

  // Constructor to initialize the list of trackers
  public ActivityTracker() {
    activityTracker = new ArrayList<>();
  }

  // Add a new tracker to the list
  public void addTracker(ActivityTracker tracker) {
    activityTracker.add(tracker);
  }

  // Find a tracker for a specific operator
  public ActivityTracker findTracker(Operator operator) {
    for (ActivityTracker tracker : activityTracker) {
      if (tracker.getOperator().equals(operator)) {
        return tracker;
      }
    }
    return null;
  }

  //  Increment the activity count and return the new count
  public String generateNextActivityId() {
    activityCount++;
    return operator.getId() + "-" + String.format("%03d", activityCount);
  }

  // Getters
  public Operator getOperator() {
    return operator;
  }

  public int getActivityCount() {
    return activityCount;
  }
}
