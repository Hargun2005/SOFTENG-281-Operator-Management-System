package nz.ac.auckland.se281;

public class PublicReview extends Review {
  private boolean isAnonymous;
  private boolean isEndorsed;

  public boolean isAnonymous() {
    return isAnonymous;
  }

  public PublicReview(
      String reviewId, String reviewerName, int rating, String comments, boolean isAnonymous) {
    super(reviewId, getReviewerName(isAnonymous, reviewerName), rating, comments);
    this.isAnonymous = isAnonymous;
    this.isEndorsed = false;
  }

  private static String getReviewerName(boolean isAnonymous, String reviewerName) {
    if (isAnonymous) {
      return "Anonymous";
    } else {
      return reviewerName;
    }
  }

  public void endorse() {
    this.isEndorsed = true;
  }

  @Override
  public void display() {
    // MessageCli to format the output
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(
        String.valueOf(rating), "5", "Public", reviewId, reviewerName);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(comments);
    if (isEndorsed) {
      MessageCli.REVIEW_ENTRY_ENDORSED.printMessage();
    }
  }
}
