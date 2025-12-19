//Five Letter Word  class to be used in the Wordle program. This class extends the Word class. And implements the abstract methods.
import java.util.*;
import java.util.ArrayList;
public class FiveLetterWord extends Word{
  // Constructor
  private static int length = 5;

  public FiveLetterWord(String[] wordList) {
    super(wordList);
    super.setLength(5);
  }
  // Abstract methods implemented
  public String chooseWord(){
    // Choose a random word from the word list in the array list specifically. Returns the word in uppercase and removes it from the array list.
    String word = "";
    ArrayList <String> words = super.getWords();
    Random rand = new Random();
    int index = rand.nextInt(super.getWords().size());
    word = getWords().get(index).toUpperCase();
    words.remove(index);
    super.setWords(words);
    return word;
  }

  public boolean isValidWord(String word){
    if (word.length() != length){
      return false;
    }
    return true;
  }
  public boolean isValidGuess(String word){
    if (word.length() != length){
      return false;
    }
    return true;
  }
  public void guessWord(String word){
    if (!isValidGuess(word)){
      throw new IllegalArgumentException("Invalid guess");
    } else {
      super.setGuess(word);
    }
  }


}