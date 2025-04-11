package nz.ac.auckland.se281;

/**
 * The FirstLetters class provides a utility method to extract the first letters of each word in a
 * given string. This can be used to generate initials or abbreviations.
 */
public class FirstLetters {
  // Extracts the first letters of each word in the given operator string and returns them as a
  // concatenated string in uppercase..
  public String getFirstLetters(String operatorName) {
    operatorName = operatorName.toUpperCase(); // Convert to uppercase for consistency
    String[] words = operatorName.split(" "); // Split the string into words
    String result = ""; // Initialize an empty string to store the result
    for (String word : words) {
      result += word.charAt(0); // Append the first character of each word
    }
    return result; // Return the concatenated result
  }
}
