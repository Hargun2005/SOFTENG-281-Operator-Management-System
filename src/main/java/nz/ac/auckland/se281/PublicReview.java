package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private boolean isAnonymous;
  private boolean isEndorsed;

  public PublicReview(
      String reviewId, String reviewerName, int rating, String comments, boolean isAnonymous) {
    super(reviewId, isAnonymous ? "Anonymous" : reviewerName, rating, comments);
    this.isAnonymous = isAnonymous;
    this.isEndorsed = false;
  }

  public void endorse() {
    this.isEndorsed = true;
  }

  @Override
  public void display() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'display'");
  }
}
