package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

public class Activity {
  private String name; // The name of the activity
  private Types.ActivityType type; // The type of the activity
  private String activityId; // The unique ID of the activity
  private List<Review> reviews = new ArrayList<>();

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

  public List<Review> getReviews() {
    return reviews;
  }

  public void addReview(Review review) {
    reviews.add(review);
  }
}
