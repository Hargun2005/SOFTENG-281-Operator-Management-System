package nz.ac.auckland.se281;

import java.util.ArrayList;
import java.util.List;

public class ExpertReview extends Review {
  private boolean recommended;
  private List<String> images;

  public ExpertReview(
      String reviewId, String reviewerName, int rating, String comments, boolean recommended) {
    super(reviewId, reviewerName, rating, comments);
    this.recommended = recommended;
    this.images = new ArrayList<>();
  }

  public void addImage(String imageName) {
    images.add(imageName);
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
    if (images != null && !images.isEmpty()) {
      MessageCli.REVIEW_ENTRY_IMAGES.printMessage(String.join(",", images)); // Use String.join
    }
  }
}
