package nz.ac.auckland.se281;

public class PrivateReview extends Review {
  private String email;
  private boolean followUpRequested;
  private String response;

  public PrivateReview(
      String reviewId,
      String reviewerName,
      String email,
      int rating,
      String comments,
      boolean followUpRequested) {
    super(reviewId, reviewerName, rating, comments);
    this.email = email;
    this.followUpRequested = followUpRequested;
    this.response = "-";
  }

  public void setResponse(String response) {
    this.response = response;
  }

  @Override
  public void display() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'display'");
  }
}
