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
    // Use MessageCli templates to format the output
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(
        String.valueOf(rating), "5", "Expert", reviewId, reviewerName);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(comments);
    if (recommended) {
      MessageCli.REVIEW_ENTRY_RECOMMENDED.printMessage();
    }
  }
}
