# W05 Practical

## Overview

The specification required that a program which takes in two arguments.

* `Argument 1`: The location of the diction of words.
* `Arguments 2`: A string of text

The program then removes 1 consonant from each word in the string of text and checks that that string of that had the consonant removed is contained in the dictionary of words and if it is then it adds it to the list of alternatives for that word. The program then outputs all the alternatives for the words to the 

### Problem Decomposition

* Read the dictionary file
* Make the the dictionary of words into a set in which comparisons can be made to.
* Read the text.
* Find consonants in the text.
* Delete consonants and compare that word to words in the dictionary.
* It has also been noted words in the dictionary vary in capitalisation so for valid comparison words in the dictionary and string will never to be lowercase before comparison.

## Design

![](uml/Parser.png)

### Parser

A two class implementation was decided to fulfil the requirements of the practical. To increase code readability the functionality in which the program requires that implemented in the Parser. The general features in which the program required were abstracted into functions. Then these functions could then be called in the main class. This made the program more readable as the functionality currently occurring could be deduced from name of function and parameters.

It was decided to made the dictionary into a `HashSet` as:

*  elements in the dictionary will be unique.
* the order of the elements in dictionary doesn't matter.

it was also decided to store list of alternatives that need printing in an `ArrayList` instead of directly printing them when they are found. This is because I could the get the length of the `ArrayList` at the end on finding Consonants is ran to determine how many alternatives were found.

## Testing

### Stacscheck Output

![](/cs/home/eo32/Documents/CS1003/Practicals/W05Practical/testing/stacscheck/stacscheck.png)

## Evaluation



## Conclusion



### Difficulties

* lowercase before comparison
* removing special special characters from text

### Given More Time