import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Frontend extends JFrame {

    //---------------- 1
    // Public Constructor calling the frontend page
    public Frontend() {
        super("Kai and Ephraim's Wordle Game");
        setLocation(450, 200);
        setSize(450, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //---------------- 2
        // Setting the overall layout
        setLayout(new BorderLayout());
        //---------------- 2

        //---------------- 2.5
        // Container for the center's components and bottom's components
        JPanel container = new JPanel();
        container.setLayout(new GridLayout(1, 2, 2, 2));
        add(container, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(2, 3));
        add(bottom, BorderLayout.SOUTH);
        //---------------- 2.5

        //---------------- 3
        // Making welcome page

        //---Welcome Title (Customizing, Adding to the north section)
        JLabel welcometitle = new JLabel("Welcome to Kai and Ephraim's Wordle game", SwingConstants.CENTER);
        welcometitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(welcometitle, BorderLayout.NORTH);

        //---How to play (Customizing)
        JLabel htp = new JLabel("How To Play?", SwingConstants.CENTER);
        htp.setFont(new Font("Tahoma", Font.BOLD, 13));
        container.add(htp);

        //---intro to wordle(Customizing)
        JTextArea summary = new JTextArea("You have 6 guesses to find a 5-letter word.\n\n" +
                "After each guess, tiles show feedback:\n\n" +
                "- Green = right letter, right spot\n" +
                "- Yellow = right letter, wrong spot\n" +
                "- Gray = letter not in the word\n\n" +
                "Use this feedback to adjust your next guesses.\n" +
                "Win by making the whole word green within 6 tries");
        summary.setEditable(false);
        summary.setLineWrap(true);
        summary.setFocusable(false);
        summary.setWrapStyleWord(true);
        summary.setOpaque(false);
        summary.setFont(new Font("Tahoma", Font.PLAIN, 13));
        container.add(summary);

        //---Making Easy, Medium, Hard Button
        JLabel easylabel = new JLabel("4 Letter Word", SwingConstants.CENTER);
        JLabel mediumlabel = new JLabel("5 Letter Word", SwingConstants.CENTER);
        JLabel hardlabel = new JLabel("6 Letter Word", SwingConstants.CENTER);
        JButton easy = new JButton("Easy");
        JButton medium = new JButton("Medium");
        JButton hard = new JButton("Hard");
        bottom.add(easylabel);
        bottom.add(mediumlabel);
        bottom.add(hardlabel);
        bottom.add(easy);
        bottom.add(medium);
        bottom.add(hard);
        //---------------- 3

    }
    // ---------------- 1

    public static void main(String[] args) {
        Frontend frontend = new Frontend();
        frontend.setVisible(true);
    }
}
