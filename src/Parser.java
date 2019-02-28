import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

/**
* Parses the dictionary file and allows for searching through it.
*/
public class Parser {

  //Stores the Dictionary of Words
  private HashSet<String> dictionary = new HashSet<String>();
  private ArrayList<String> alternatives = new ArrayList<String>();

  /**
  * Takes in a file with the dictionary of words and makes into the Dictionary
  of words in which constants which are taking out will be compared to.
  *
  * @param fileName the name of the file being inputed to the program as a dictionary.
  */
  public void loadDictionary(String fileName) {

    try {
      BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        dictionary.add(line.toLowerCase());
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
  * To verify that the dictionary has recieved input when loading.
  *
  * @return true is dictionary of words is not empty.
  */
  public boolean validDictionary() {

    return !this.dictionary.isEmpty();
  }

  /**
  * Finds if a specific character is a consontant.
  *
  * @param character the character being checked
  * @param isVowel determines if finding vowels or consonants
  * @return true is a consontant is found.
  */
  private boolean letterType(char character, boolean isVowel) {

    //lowest case before comparison
    String sCharacter = Character.toString(character).toLowerCase();
    if (isVowel){
      return sCharacter.matches("[aeiou]");
    }
    else {
      return sCharacter.matches("[b-df-hj-np-tv-z]");
    }
  }

  /**
  * Checks if a word is in the dictionary of words.
  *
  * @param word the word being searched in the dictionary
  * @return true if is present in the dictionary of words.
  */
  private boolean isInDictionary(String word) {

    //lowercase for comparison
    //removes all non-letter characters using REGEX precomparison.
    return this.dictionary.contains(word.toLowerCase().replaceAll("[^a-z]", ""));
  }

  /**
  * Determines which words have avaiable alternatives in the dictionary.
  *
  * @param forVowels determing if you are finding vowels or consonants
  * @param words the words which are contained in the sentence
  */
  public void findLostLetters(String[] words, boolean isVowel) {

    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      for (int j = 0; j < word.length(); j++) {
        if (this.letterType(word.charAt(j), isVowel)) {
          //used to delete chars at specific indexs
          StringBuilder builder = new StringBuilder(word);
          String lostCon = builder.deleteCharAt(j).toString();
          //deteletes character at a location and the converts it back to string.
          if (this.isInDictionary(lostCon)) {
            String newAlternative = "";
            for (int q = 0; q < words.length; q++) {
              if (i == q) {
                newAlternative += lostCon +  " ";
              }
              else {
                newAlternative += words[q] + " ";
              }
            }
            //deletes extra space
            newAlternative = newAlternative.substring(0, newAlternative.length() - 1);
            this.alternatives.add(newAlternative);
          }
        }
      }
    }
  }

  /**
  * Prints the alternatives to terminal.
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
