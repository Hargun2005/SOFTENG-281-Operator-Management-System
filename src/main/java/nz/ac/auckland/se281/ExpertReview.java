package nz.ac.auckland.se281;

public class ExpertReview extends Review {
  private boolean recommended;

  public ExpertReview(
      String reviewId, String reviewerName, int rating, String comments, boolean recommended) {
    super(reviewId, reviewerName, rating, comments);
    this.recommended = recommended;
  }

  @Override
  public void display() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'display'");
  }
}
