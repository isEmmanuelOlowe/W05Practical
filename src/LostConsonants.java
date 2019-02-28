/**
* Deletes constants from words and sees if new word exist in dictionary supplied.
*/
public class LostConsonants {

  /**
  * Deletes constants from words and sees if new word in exist in dictionary supplied.
  *
  * @param args [0] the location of the dictionary [1] the sentence or word being parsed.
  */
  public static void main(String[] args) {

    //The program expects two arguments
    if (args.length == 2 || (args.length == 3 && args[2].equals("-v"))) {
      String dictionaryFileName = args[0];
      String text = args[1];
      Parser parser = new Parser();
      parser.loadDictionary(dictionaryFileName);
      //splits the text by all the white space it contains.
      String[] words = text.split("\\s+");
      boolean forVowels = args.length == 3? true : false;
      if (parser.validDictionary()) {
        parser.findLostLetters(words, forVowels);
        parser.printAlternatives();

      }

    }
    else if (args.length == 3 && args[2].equals("-A")) {

      String dictionaryLocation = args[0];
      String text = args[1];
      
      Matcher matcher = new Matcher(text);
      matcher.loadText(dictionaryLocation);
      matcher.printAlternatives();
    }
     else {
      System.out.println("Expected 2 or 3 command line arguments, but got " + args.length + ".");
      System.out.println("Please provide the path to the dictionary file as the first"
      + " argument and a sentence as the second argument.");
      System.out.println("Add '-v' to have process ran for vowels");
    }
  }
}
