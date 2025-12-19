import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FrontendEasy extends JFrame {
    String[] wordlist = { "your", "have", "more", "will", "home", "page", "free", "time", "they", "site", "what",
            "news", "only", "when", "here", "also", "help", "view", "been", "were", "some", "like", "than", "find",
            "date", "back", "list", "name", "just", "over", "year", "into", "next", "used", "work", "last", "most",
            "data", "make", "them", "post", "city", "such", "best", "then", "good", "info", "high", "each", "very",
            "book", "read", "need", "many", "user", "said", "does", "mail", "full", "life", "know", "days", "part",
            "real", "item", "must", "made", "line", "send", "type", "take", "area", "want", "long", "code", "show",
            "even", "much", "sign", "file", "link", "open", "case", "same", "both", "game", "care", "down", "size",
            "shop", "text", "rate", "form", "love", "john", "main" };
    Word wordle = new FourLetterWord(wordlist);

    int currentRow = 0;

    // ---------------- 1
    // Public Constructor calling the frontend page
    public FrontendEasy() {
        super("Wordle Easy Mode");
        wordle.setWord(wordle.chooseWord()); // Pick a random word
        setLocation(450, 200);
        setSize(450, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ---------------- 2
        // Setting the overall tile layout
        setLayout(new GridLayout(6, 4));
        // ---------------- 2

        // ---------------- 3
        // Creating the tiles and adding game functionality
        JTextField[][] tile = new JTextField[6][4];
        // ---Word Font
        Font wordfont = new Font("Tahoma", Font.BOLD, 30);

        // ---Creating the tiles and setting their properties
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                tile[row][col] = new JTextField();
                tile[row][col].setFont(wordfont);
                tile[row][col].setHorizontalAlignment(JTextField.CENTER);
                tile[row][col].setEditable(false);
                tile[row][col].setFocusable(false);
                tile[row][col].setFont(wordfont);
                add(tile[row][col]);
            }
        }

        // ---only the first row initially
        for (int col = 0; col < 4; col++) {
            tile[0][col].setEditable(true);
            tile[0][col].setFocusable(true);
        }

        // ---Adding key listeners to the tiles
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 4; col++) {
                final int r = row; // had to do this since java was complaining
                final int c = col; // saying it had to be in final
                tile[r][c].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char stufftyped = e.getKeyChar(); // getting the character that was typed
                        e.setKeyChar(Character.toUpperCase(stufftyped)); // uppercasing the character
                        if (tile[r][c].getText().length() >= 1) { // limiting the character to 1
                            e.consume();
                        }
                    }

                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) { // if the key pressed is enter
                            if (r != currentRow) {
                                return; // Only process enter for the current row
                            }
                            String guess = ""; // using the string to store the guess, by concatenating the keys typed
                            for (int i = 0; i < 4; i++) {
                                guess += tile[r][i].getText();
                            }

                            wordle.guessWord(guess);
                            int[] results = wordle.checkGuess(guess);
                            int[][] colors = wordle.colorHints(results);

                            // ---Color the tiles
                            for (int i = 0; i < 4; i++) {
                                tile[currentRow][i].setBackground(new Color(colors[i][0], colors[i][1], colors[i][2]));
                            }

                            if (wordle.isSolved(results)) {
                                JOptionPane.showMessageDialog(null, "You guessed right!");
                                currentRow = 6; // Stop game
                                // ---Disable current row
                                for (int i = 0; i < 4; i++) {
                                    tile[r][i].setEditable(false);
                                    tile[r][i].setFocusable(false);
                                }
                                FrontendEasy.this.dispose(); // back to main menu
                                new WordleGame().setVisible(true);
                            } else {
                                // ---Disable current row
                                for (int i = 0; i < 4; i++) {
                                    tile[r][i].setEditable(false);
                                    tile[r][i].setFocusable(false);
                                }

                                currentRow += 1;
                                if (currentRow < 6) {
                                    // ---Enable next row
                                    for (int i = 0; i < 4; i++) {
                                        tile[currentRow][i].setEditable(true);
                                        tile[currentRow][i].setFocusable(true);
                                    }
                                    tile[currentRow][0].requestFocus();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Game Over! The word was " + wordle.getWord());
                                    FrontendEasy.this.dispose();
                                    new WordleGame().setVisible(true); // back to main menu
                                }
                            }
                        }
                    }
                });

            }

        }
        // ---------------- 3
    }
    // ---------------- 1
}
