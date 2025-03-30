package nz.ac.auckland.se281;

/**
 * The FirstLetters class provides a utility method to extract the first letters of each word in a
 * given string. This can be used to generate initials or abbreviations.
 */
public class FirstLetters {
  // Extracts the first letters of each word in the given operator string and returns them as a
  // concatenated string in uppercase..
  public String getFirstLetters(String operatorName) {
    operatorName =
        operatorName.toUpperCase(); // Convert the input string to uppercase for consistency
    String[] words =
        operatorName.split(" "); // Split the string into words using spaces as delimiters
    StringBuilder result =
        new StringBuilder(); // Use a StringBuilder to efficiently build the result
    for (String word :
        words) { // Iterate through each word and append its first character to the result
      result.append(word.charAt(0));
    }
    return result.toString();
  }
}
