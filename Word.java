// Word class to be used in the Wordle program. This is an abstract class as not all of the methods will be implemented in this class.
import java.util.ArrayList;

public abstract class Word {
  // Instance variables
  private String word;
  private int length;
  private static int totalGuesses = 5;
  private int guessesLeft;
  private String guess;
  private boolean isSolved;
  private boolean isValidWord;
  private ArrayList<String> words;
  private String[] wordList;

  // Constructor
  public Word(String[] wordList) {
    if (!isValidState(wordList)) {
      throw new IllegalArgumentException("Invalid word list");
    }
    this.words = new ArrayList<String>();
    this.wordList = new String[wordList.length];
    for (int i = 0; i < wordList.length; i++) {
      this.wordList[i] = wordList[i];
      this.words.add(wordList[i]);
    }
    guessesLeft = totalGuesses;
  }

  // Getters and Setters
  public String getWord() {
    return this.word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public int getLength() {
    return this.length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public static int getTotalGuesses() {
    return totalGuesses;
  }

  public int getGuessesLeft() {
    return this.guessesLeft;
  }

  public void setGuessesLeft(int guessesLeft) {
    this.guessesLeft = guessesLeft;
  }

  public boolean getIsSolved() {
    return this.isSolved;
  }

  public void setIsSolved(boolean isSolved) {
    this.isSolved = isSolved;
  }

  public boolean getIsValidWord() {
    return this.isValidWord;
  }

  public ArrayList<String> getWords() {
    ArrayList<String> wordsCopy = new ArrayList<String>();
    for (int i = 0; i < this.words.size(); i++) {
      wordsCopy.add(this.words.get(i));
    }
    return wordsCopy;
  }

  public void setWords(ArrayList<String> words) {
    this.words = new ArrayList<String>();
    for (int i = 0; i < words.size(); i++) {
      this.words.add(words.get(i));
    }
  }

  public String[] getWordList() {
    String[] wordListCopy = new String[this.wordList.length];
    for (int i = 0; i < this.wordList.length; i++) {
      wordListCopy[i] = this.wordList[i];
    }
    return wordListCopy;
  }

  public void setWordList(String[] wordList) {
    this.wordList = new String[wordList.length];
    for (int i = 0; i < wordList.length; i++) {
      this.wordList[i] = wordList[i];
    }
  }

  public String getGuess() {
    return this.guess;
  }

  public void setGuess(String guess) {
    this.guess = guess;
  }

  // Abstract methods
  public abstract String chooseWord();

  public abstract boolean isValidWord(String word);

  public abstract boolean isValidGuess(String word);

  public abstract void guessWord(String word);

  // Private methods
  private boolean isValidState(String[] wordList) {
    if (wordList == null || wordList.length == 0) {
      return false;
    }
    for (int i = 0; i < wordList.length; i++) {
      if (!isValidWord(wordList[i])) {
        return false;
      }
    }
    return true;
  }

  // Public methods
  public int checkCharacter(String guess, int index) {
    /*
     * Checks to see if the character is in the word and if it is in the correct
     * position
     * Returns 1 if the character is in the word and in the correct position
     * Returns 2 if the character is in the word but not in the correct position
     * Returns 0 if the character is not in the word
     */
    if (guess.charAt(index) == this.word.charAt(index)) {
      return 1;
    } else {
      for (int i = 0; i < this.word.length(); i++) {
        if (guess.charAt(index) == this.word.charAt(i)) {
          return 2;
        }
      }
    }
    return 0;
  }

  public int[] checkGuess(String guess) {
    /*
     * Checks to see if the guess is correct and gives hints based on the guess.
     * Returns an array of integers where each integer represents the result of the
     * checkCharacter method.
     * Returns null if the guess is not valid.
     */
    if (!isValidGuess(guess)) {
      throw new NullPointerException("Invalid guess");
    } else {
      int[] result = new int[guess.length()];
      for (int i = 0; i < guess.length(); i++) {
        result[i] = checkCharacter(guess, i);
      }
      return result;
    }
  }

  public boolean isSolved(int[] nums) {
    /*
     * Checks to see if the guess is correct and if the game is solved.
     * Returns true if the guess is correct and the game is solved.
     * Returns false if the guess is not correct and the game is not solved.
     */
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != 1) {
        guessesLeft--;
        return false;
      }
    }
    return true;
  }

  public boolean isGameOver() {
    /*
     * Checks to see if the game is over.
     * Returns true if the game is over.
     * Returns false if the game is not over.
     */
    if (this.guessesLeft == 0 || this.isSolved) {
      return true;
    }
    return false;
  }

  public int[][] colorHints(int[] nums) {
    /*
     * Returns a 2D array of integers. Each integer represents the color of the
     * hint.
     * 0 in all 3 positions means the character is not in the word and should be
     * displayed as black in the GUI using the color class.
     * 255 in the first and second position and 0 in the third position means the
     * character is in the word but not in the correct position and should be
     * displayed as yellow in the GUI using the color class.
     * 255 in the second position and 0 in the first and third position means the
     * character is in the word and in the correct position and should be displayed
     * as green in the GUI using the color class.
     */
    int[][] colors = new int[nums.length][3];
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 0) {
        colors[i][0] = 166;
        colors[i][1] = 166;
        colors[i][2] = 166;
      } else if (nums[i] == 2) {
        colors[i][0] = 228;
        colors[i][1] = 212;
        colors[i][2] = 76;
      } else if (nums[i] == 1) {
        colors[i][0] = 53;
        colors[i][1] = 218;
        colors[i][2] = 78;
      }
    }
    return colors;
  }
}