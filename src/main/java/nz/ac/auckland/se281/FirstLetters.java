package nz.ac.auckland.se281;

public class FirstLetters {
  public String getFirstLetters(String operatorName) {
    operatorName = operatorName.toUpperCase();
    String[] words = operatorName.split(" ");
    StringBuilder result = new StringBuilder();
    for (String word : words) {
      result.append(word.charAt(0));
    }
    return result.toString();
  }
}
