package nz.ac.auckland.se281;

public abstract class Review {
  protected String reviewId;
  protected String reviewerName;
  protected int rating;
  protected String comments;

  public Review(String reviewId, String reviewerName, int rating, String comments) {
    this.reviewId = reviewId;
    this.reviewerName = reviewerName;
    this.rating = Math.max(1, Math.min(5, rating)); // Ensure rating is between 1 and 5
    this.comments = comments;
  }

  public String getReviewId() {
    return reviewId;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public int getRating() {
    return rating;
  }

  public String getComments() {
    return comments;
  }

  // Abstract method to display review details
  public abstract void display();
}
