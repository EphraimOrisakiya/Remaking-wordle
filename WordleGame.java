import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WordleGame extends JFrame {

    //---------------- 1
    // Public Constructor calling the frontend page
    /*  Next steps : Add an action listener that checks if 
    * the user clicked the return or enter button after typing ALL the letters in 
    * if true, then the back end methods in the word class will check the guess. 
    */
    public WordleGame() {
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
        // Making welcome page UI

        //---Welcome Title (Customizing, Adding to the north section)
        JLabel welcometitle = new JLabel("Kai and Ephraim's Wordle Game", SwingConstants.CENTER);
        welcometitle.setForeground(new Color(0, 0, 0));
        welcometitle.setFont(new Font("Tahoma", Font.BOLD, 25));
        welcometitle.setOpaque(true);
        add(welcometitle, BorderLayout.NORTH);

        //---How to play (Customizing)
        JLabel htp = new JLabel("How To Play?", SwingConstants.CENTER);
        htp.setFont(new Font("Tahoma", Font.BOLD, 13));
        container.add(htp);

        //---intro to wordle(Customizing)
        JTextArea summary = new JTextArea("You have 6 guesses to find a 4 to 6-letter word.\n\n" +
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

        //---------------- 4
        // Adding functionality to buttons

        //---Listener for Easy, Medium, and Hard
        easy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrontendEasy frontendEasy = new FrontendEasy();
                frontendEasy.setVisible(true);
                WordleGame.this.dispose();
            }
        });
        medium.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrontendMedium frontendMedium = new FrontendMedium();
                frontendMedium.setVisible(true);
                WordleGame.this.dispose();
            }
        });
        hard.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                FrontendHard frontendHard = new FrontendHard();
                frontendHard.setVisible(true);
                WordleGame.this.dispose();
            }
        });
        //---------------- 4

        //---------------- 5
        // Customizing the ui
        easy.setBackground(new Color(53, 218, 78));
        medium.setBackground(new Color(228, 212, 76));
        hard.setBackground(new Color(166, 166, 166));
        bottom.setBackground(new Color(250, 250, 250));
        welcometitle.setBackground(new Color(250, 250, 250));
        container.setBackground(new Color(250, 250, 250));
        easy.setForeground(new Color(0, 0, 0));
        medium.setForeground(new Color(0, 0, 0));
        hard.setForeground(new Color(0, 0, 0));
        easylabel.setForeground(new Color(0, 0, 0));
        mediumlabel.setForeground(new Color(0, 0, 0));
        hardlabel.setForeground(new Color(0, 0, 0));
        summary.setForeground(new Color(0, 0, 0));
        
    }
    // ---------------- 1

    public static void main(String[] args){
        WordleGame frontend = new WordleGame();
        frontend.setVisible(true);
    }
}
