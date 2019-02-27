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
    if (args.length == 2) {
      String dictionaryFileName = args[0];
      String text = args[1];
      Parser parser = new Parser();
      parser.loadDictionary(dictionaryFileName);
      //splits the text by all the white space it contains.
      String[] words = text.split("\\s+");

      if (parser.validDictionary()) {
        parser.findLostConsonances(words);
        parser.printAlternatives();

      }

    }
    else {
      System.out.println("Expected 2 command line arguments, but got " + args.length + ".");
      System.out.println("Please provide the path to the dictionary file as the first"
      + " argument and a sentence as the second argument.");
    }
  }
}
