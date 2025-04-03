package nz.ac.auckland.se281;

public class Activity {
  private String name; // The name of the activity
  private Types.ActivityType type; // The type of the activity
  private String activityId; // The unique ID of the activity

  public Activity(String name, Types.ActivityType type, String activityId) {
    this.name = name;
    this.type = type;
    this.activityId = activityId;
  }

  public String getName() {
    return name;
  }

  public Types.ActivityType getType() {
    return type;
  }

  public String getActivityId() {
    return activityId;
  }
}
