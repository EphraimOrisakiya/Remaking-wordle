import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class FrontendHard extends JFrame {
    String[] wordlist = { "writer", "search", "online", "people", "health", "should", "system", "policy", "number",
            "please", "rights", "public", "school", "review", "united", "center", "travel", "report", "member",
            "before",
            "hotels", "office", "design", "posted", "within", "states", "family", "prices", "sports", "county",
            "access", "change", "rating", "during", "return", "events", "little", "movies", "source", "author",
            "around", "course", "canada", "credit", "estate", "select", "photos", "thread", "market", "really",
            "action", "series", "second", "forums", "better", "friend", "server", "issues", "street", "things",
            "person", "mobile", "offers", "recent", "stores", "memory", "social", "august", "create", "single",
            "latest", "status", "browse", "seller", "always", "result", "groups", "making", "future", "london",
            "become", "garden", "listed", "energy", "images", "notice", "others", "format", "months", "safety",
            "having", "common", "living", "called", "period", "window", "france", "region", "island", "record",
            "direct" };
    Word wordle = new SixLetterWord(wordlist);
    int currentRow = 0;

    // ---------------- 1
    // Public Constructor calling the frontend page
    public FrontendHard() {
        super("Wordle Hard Mode");
        wordle.setWord(wordle.chooseWord()); // Pick a random word
        setLocation(450, 200);
        setSize(450, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // ---------------- 2
        // Setting the overall tile layout
        setLayout(new GridLayout(6, 6));
        // ---------------- 2

        // ---------------- 3
        // Creating the tiles and adding game functionality
        JTextField[][] tile = new JTextField[6][6];
        // Word Font
        Font wordfont = new Font("Tahoma", Font.BOLD, 30);

        // Creating the tiles and setting their properties
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                tile[row][col] = new JTextField();
                tile[row][col].setFont(wordfont);
                tile[row][col].setHorizontalAlignment(JTextField.CENTER);
                tile[row][col].setEditable(false);
                tile[row][col].setFocusable(false);
                tile[row][col].setFont(wordfont);
                add(tile[row][col]);
            }
        }

        // ---Enable only the first row initially
        for (int col = 0; col < 6; col++) {
            tile[0][col].setEditable(true);
            tile[0][col].setFocusable(true);
        }

        // ---Adding key listeners to the tiles
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
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
                            for (int i = 0; i < 6; i++) {
                                guess += tile[r][i].getText();
                            }

                            wordle.guessWord(guess);
                            int[] results = wordle.checkGuess(guess);
                            int[][] colors = wordle.colorHints(results);

                            // ---Color the tiles
                            for (int i = 0; i < 6; i++) {
                                tile[currentRow][i].setBackground(new Color(colors[i][0], colors[i][1], colors[i][2]));
                            }

                            if (wordle.isSolved(results)) {
                                JOptionPane.showMessageDialog(null, "You guessed right!");
                                currentRow = 6; // Stop game
                                // ---Disable current row
                                for (int i = 0; i < 6; i++) {
                                    tile[r][i].setEditable(false);
                                    tile[r][i].setFocusable(false);
                                }
                                FrontendHard.this.dispose();
                                new WordleGame().setVisible(true); // back to main menu
                            } else {
                                // ---Disable current row
                                for (int i = 0; i < 6; i++) {
                                    tile[r][i].setEditable(false);
                                    tile[r][i].setFocusable(false);
                                }

                                currentRow += 1;
                                if (currentRow < 6) {
                                    // ---Enable next row
                                    for (int i = 0; i < 6; i++) {
                                        tile[currentRow][i].setEditable(true);
                                        tile[currentRow][i].setFocusable(true);
                                    }
                                    tile[currentRow][0].requestFocus();
                                } else {
                                    JOptionPane.showMessageDialog(null, "Game Over! The word was " + wordle.getWord());
                                    FrontendHard.this.dispose();
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