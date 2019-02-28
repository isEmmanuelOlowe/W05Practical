import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
* Creates regex pattern for every word and sees if it can be matched to pattern of
word in dictionary supplied.
*/
public class Matcher {

  private String[] text;
  private ArrayList<String> regexPatterns = new ArrayList<String>();
  private ArrayList<String> alternatives = new ArrayList<String>();


  /**
  * Creates regex pattern for adding consonants for every word in the text.
  *
  * @param unformattedText the text which shall be made into the regex pattern.
  */
  public Matcher(String unformattedText){

    this.text = unformattedText.split("\\s+");
    this.patterniser();
  }

  /**
  * Stream reads file and sees if any words exist which matches regex pattern
  *
  * @param fileName the name of the file to be used a dictionary
  */
  public void loadText(String fileName) {

    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        for (int i = 0; i < this.text.length; i++){
          //checks matches pattern but is not the same
          if (!line.toLowerCase().equals(text[i])&&line.toLowerCase().matches(regexPatterns.get(i))){
            for (int j = 0; j < this.text.length; j++) {
              String newAlternative = "";
              if (i == j){
                newAlternative += line.toLowerCase() + " ";
              }
              else {
                newAlternative += text[j] + " ";
              }
              //deletes extra space
              newAlternative = newAlternative.substring(0, newAlternative.length() - 1);
              this.alternatives.add(newAlternative);
            }
          }
        }
      }
      bufferedReader.close();
    }
    catch (FileNotFoundException e) {
      System.out.println("File not found: " + fileName + " (No such file or directory)");
      System.out.println("Invalid dictionary, aborting.");
    }
    catch (IOException e) {
      System.out.println("Error Reading File: " + e.getMessage());
    }
  }

  /**
  * Creates regex patterns for all the words in the text.
  */
  private void patterniser() {

    for (String word: this.text) {
      this.regexPatterns.add(this.createPattern(word));
    }
  }
  /**
  * Creates the regex pattern for the word that will be used to search the dictionary.
  *
  * @param word the word the pattern is being created for
  * @return the regex pattern to find possible consonant insertions.
  */
  public String createPattern(String word) {

    //formats the word
    String fWord = word.toLowerCase().replaceAll("[\\W]", "");
    String regexPattern = "";
    for(int i = 0; i < fWord.length(); i++) {
      regexPattern += "[b-df-hj-np-tv-z]*[" + fWord.substring(i, i + 1) +"]";
    }
    //adds extra
    regexPattern += "[b-df-hj-np-tv-z]*";
    return regexPattern;
  }

  /**
  * Prints out all the alternatives which exist inside the dictionary.
  */
  public void printAlternatives() {

    if (this.alternatives.size() > 0) {
      for (String alternative: this.alternatives) {
        System.out.println(alternative);
      }
      System.out.println("Found " + this.alternatives.size() + " alternatives.");
    }
    else {
      System.out.println("Could not find any alternatives.");
    }
  }
}
