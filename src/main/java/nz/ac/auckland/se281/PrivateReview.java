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
    // Use MessageCli templates to format the output
    MessageCli.REVIEW_ENTRY_HEADER.printMessage(
        String.valueOf(rating), "5", "Private", reviewId, reviewerName);
    MessageCli.REVIEW_ENTRY_REVIEW_TEXT.printMessage(comments);
    if (followUpRequested && response.equals("-")) {
      MessageCli.REVIEW_ENTRY_FOLLOW_UP.printMessage(email);
    } else {
      MessageCli.REVIEW_ENTRY_RESOLVED.printMessage(response);
    }
  }
}
