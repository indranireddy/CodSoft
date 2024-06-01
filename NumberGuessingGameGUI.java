import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameGUI extends JFrame {

    private static final int MAX_ATTEMPTS = 10;
    private int numberToGuess;
    private int attempts;
    private boolean hasWon;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JButton playAgainButton;
    private JButton resetButton;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        initializeGame();

        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(173, 216, 230));
        headerPanel.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Number Guessing Game...", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(new Color(70, 130, 180));
        headerPanel.add(headerLabel, BorderLayout.CENTER);

        add(headerPanel, BorderLayout.NORTH);

        // Center panel for feedback and attempts
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.setBackground(new Color(245, 245, 245));

        feedbackLabel = new JLabel("Generated a random number between 1 and 100.", JLabel.CENTER);
        feedbackLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        feedbackLabel.setForeground(new Color(70, 130, 180));
        centerPanel.add(feedbackLabel);

        attemptsLabel = new JLabel("Attempts: 0", JLabel.CENTER);
        attemptsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        attemptsLabel.setForeground(new Color(70, 130, 180));
        centerPanel.add(attemptsLabel);

        add(centerPanel, BorderLayout.CENTER);

        // Input panel for guess field and guess button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBackground(new Color(245, 245, 245));

        guessField = new JTextField(10);
        guessButton = new JButton("Guess");
        guessButton.setBackground(new Color(144, 238, 144));
        guessButton.setForeground(Color.DARK_GRAY);
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));
        guessButton.addActionListener(new GuessButtonListener());

        inputPanel.add(new JLabel("Enter your guess: "));
        inputPanel.add(guessField);
        inputPanel.add(guessButton);

        add(inputPanel, BorderLayout.SOUTH);

        // Side panel for play again and reset buttons
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new GridLayout(2, 1, 10, 10));
        sidePanel.setBackground(new Color(245, 245, 245));

        playAgainButton = new JButton("Play Again");
        playAgainButton.setBackground(new Color(255, 182, 193));
        playAgainButton.setForeground(Color.DARK_GRAY);
        playAgainButton.setFont(new Font("Arial", Font.BOLD, 14));
        playAgainButton.setEnabled(false);
        playAgainButton.addActionListener(new PlayAgainButtonListener());

        resetButton = new JButton("Reset");
        resetButton.setBackground(new Color(255, 228, 181));
        resetButton.setForeground(Color.DARK_GRAY);
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.addActionListener(new ResetButtonListener());

        sidePanel.add(playAgainButton);
        sidePanel.add(resetButton);

        add(sidePanel, BorderLayout.EAST);

        setVisible(true);
    }

    private void initializeGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        hasWon = false;
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                attempts++;

                if (userGuess == numberToGuess) {
                    hasWon = true;
                    feedbackLabel.setText("Congratulations! You guessed the number in " + attempts + " attempts.");
                    feedbackLabel.setForeground(new Color(34, 139, 34)); // Green color for success
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                    resetButton.setEnabled(true);
                } else if (userGuess > numberToGuess) {
                    feedbackLabel.setText("Too high! Try again.");
                    feedbackLabel.setForeground(new Color(255, 99, 71)); // Light red color for too high
                } else {
                    feedbackLabel.setText("Too low! Try again.");
                    feedbackLabel.setForeground(new Color(255, 99, 71)); // Light red color for too low
                }

                attemptsLabel.setText("Attempts: " + attempts);

                if (attempts >= MAX_ATTEMPTS && !hasWon) {
                    feedbackLabel.setText("Sorry, you've used all " + MAX_ATTEMPTS + " attempts. The number was " + numberToGuess + ".");
                    feedbackLabel.setForeground(new Color(255, 69, 0)); // Orange red color for failure
                    guessButton.setEnabled(false);
                    playAgainButton.setEnabled(true);
                    resetButton.setEnabled(true);
                }
            } catch (NumberFormatException ex) {
                feedbackLabel.setText("Please enter a valid number.");
                feedbackLabel.setForeground(new Color(255, 99, 71)); // Light red color for invalid input
            }
        }
    }

    private class PlayAgainButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            initializeGame();
            guessField.setText("");
            feedbackLabel.setText("Generated a random number between 1 and 100.");
            feedbackLabel.setForeground(new Color(70, 130, 180)); // Reset to default color
            attemptsLabel.setText("Attempts: 0");
            guessButton.setEnabled(true);
            playAgainButton.setEnabled(false);
            resetButton.setEnabled(false);
        }
    }

    private class ResetButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            guessField.setText("");
            feedbackLabel.setText("Generated a random number between 1 and 100.");
            feedbackLabel.setForeground(new Color(70, 130, 180)); // Reset to default color
            attemptsLabel.setText("Attempts: 0");
            guessButton.setEnabled(true);
            playAgainButton.setEnabled(false);
            resetButton.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
