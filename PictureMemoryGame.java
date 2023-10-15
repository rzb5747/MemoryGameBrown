import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PictureMemoryGame extends JFrame {
    private ArrayList<String> imagePaths;
    private ArrayList<String> cardImages;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex = -1;
    private int secondCardIndex;
    private String imagePath;
    private int moveCount;
    private JLabel moveLabel;
    private JLabel timeLabel;
    private int roundCount;
    private JLabel roundLabel;
    private Timer roundTime1;
    private long roundStartTime;
    private long totalRoundTime;
    private JLabel roundTimeLabel;
    private Timer gameTimer;
    private int gameDuration;
    private Timer totalGameTimer;
    private Timer roundTimer;
    private int roundCounter;

    public PictureMemoryGame(int gridSize) {
        setTitle("Picture Memory Game");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        cardImages = new ArrayList<>();



        System.out.println(imagePaths);

        Collections.shuffle(imagePaths);

        System.out.println(imagePaths);


        for (String imagePath : imagePaths) {
            cardImages.add("");
        }


        int numRows = 0;
        int numCols = 0;

        imagePaths = new ArrayList<>();
        if (gridSize == 12) {
            numRows = 4;
            numCols = 3;
            imagePaths.add("balloon1.png");

            imagePaths.add("balloon2.png");

            imagePaths.add("balloon3.png");

            imagePaths.add("balloon4.png");

            imagePaths.add("balloon5.png");

            imagePaths.add("balloon6.png");


            imagePaths.add("balloon1.png");
            imagePaths.add("balloon2.png");
            imagePaths.add("balloon3.png");
            imagePaths.add("balloon4.png");
            imagePaths.add("balloon5.png");
            imagePaths.add("balloon6.png");
Collections.shuffle(imagePaths);
        } else if (gridSize == 24) {
            numRows = 6;
            numCols = 4;
            imagePaths.add("balloon1.png");

            imagePaths.add("balloon2.png");

            imagePaths.add("balloon3.png");

            imagePaths.add("balloon4.png");

            imagePaths.add("balloon5.png");

            imagePaths.add("balloon6.png");
            imagePaths.add("balloon7.png");
            imagePaths.add("balloon8.png");
            imagePaths.add("balloon9.png");
            imagePaths.add("balloon10.png");
            imagePaths.add("balloon11.png");
            imagePaths.add("balloon12.png");


            imagePaths.add("balloon1.png");
            imagePaths.add("balloon2.png");
            imagePaths.add("balloon3.png");
            imagePaths.add("balloon4.png");
            imagePaths.add("balloon5.png");
            imagePaths.add("balloon6.png");
            imagePaths.add("balloon7.png");
            imagePaths.add("balloon8.png");
            imagePaths.add("balloon9.png");
            imagePaths.add("balloon10.png");
            imagePaths.add("balloon11.png");
            imagePaths.add("balloon12.png");
Collections.shuffle(imagePaths);

        } else {
            System.out.println("Invalid entry, please enter 12 or 24.");
        }

        gameDuration = 300;
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameDuration--;
                timeLabel.setText("Time Left: " + gameDuration + " seconds");

                if (gameDuration <= 0) {
                    gameTimer.stop();
                    JOptionPane.showMessageDialog(null, "Game Over! You've run out of time.");
                    resetGame();
                    gameDuration = 300;
                    gameTimer.start();

                    startNewGame();
                }
            }

        });;
        roundTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    totalRoundTime++;
                    roundTimeLabel.setText("Round Time: " + totalRoundTime + " seconds");

            }
        });
        cardImages = new ArrayList<>();
        for (String imagePath : imagePaths) {
            cardImages.add(imagePath);
        }
        cardButtons = new JButton[gridSize];
        JPanel cardPanel = new JPanel(new GridLayout(numRows, numCols));
        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton();
            cardButtons[i].setIcon(new ImageIcon("Question.png"));
            cardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleCardClick(index);
                }
            });
            cardPanel.add(cardButtons[i]);
        }

        moveLabel = new JLabel("Moves: " + moveCount);
        add(moveLabel, BorderLayout.SOUTH);
        add(cardPanel);

        roundCount = 1;
        roundLabel = new JLabel("Round: " + roundCount);
        add(roundLabel, BorderLayout.NORTH);

        roundStartTime = 0;
        totalRoundTime = 0;

        roundTimeLabel = new JLabel("Round Time: 0 seconds" );
        add(roundTimeLabel, BorderLayout.EAST);
        roundTimer.start();

        timeLabel = new JLabel("Time Left: " + gameDuration + " seconds");
        add(timeLabel,BorderLayout.WEST);
        gameTimer.start();
    }
    private void startNewGame() {
        resetGame();


        startRound();
    }
    private void restartGame(){
        JOptionPane.showMessageDialog(null, "Game Over! You've run out of time.");
        resetGame();
        startNewGame();
    }

    private void startRound() {
        roundStartTime = System.currentTimeMillis();
        totalRoundTime = 0;
        roundLabel.setText("Round: " + roundCount);


    }
    private void handleCardClick(int index) {
        if (cardButtons[index].getIcon() == null) {
            return;
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
            moveCount++;
            updateMoveLabel();


        } else {
            secondCardIndex = index;
            cardButtons[secondCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));



            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (imagePaths.get(firstCardIndex).equals(imagePaths.get(secondCardIndex))) {
                        cardButtons[firstCardIndex].setIcon(null);
                        cardButtons[secondCardIndex].setIcon(null);
                        cardImages.set(firstCardIndex, null);
                        cardImages.set(secondCardIndex, null);
                        numberOfMatches++;

                        if (numberOfMatches == imagePaths.size() / 2) {
                            if(roundCount < 3 ) {
                                JOptionPane.showMessageDialog(null, "Congrats! Next Round!");
                                roundCount++;
                                moveCount = 0;
                                totalRoundTime = 0;
                                resetGame();
                                updateRoundLabel();
                                startNewGame();
                            }else {JOptionPane.showMessageDialog(null, "You won! ");
                                roundCount = 0;
                                updateRoundLabel();
                                resetGame();


                            }
                        }
                    } else {
                        cardButtons[firstCardIndex].setIcon(new ImageIcon("Question.png"));
                        cardButtons[secondCardIndex].setIcon(new ImageIcon("Question.png"));
                    }
                    firstCardIndex = -1;
                }
            });
            timer.setRepeats(false);
            timer.start();
        }

    }
    private void updateMoveLabel() {
        moveLabel.setText("Moves: " + moveCount);
    }
    private void updateRoundLabel() {
        roundLabel.setText("Round: " + roundCount);
    }
    private void resetGame() {

        firstCardIndex = -1;
        secondCardIndex = 0;
        numberOfMatches = 0;


        Collections.shuffle(imagePaths);

        for (int i = 0; i < cardButtons.length; i++) {
            cardButtons[i].setIcon(new ImageIcon("Question.png"));
        }
        moveCount = 0;

    }

    private static int gridSize() {
        int gridSize = 0;
        Scanner scanner = new Scanner(System.in);
        while (gridSize != 12 && gridSize != 24) {
            System.out.println("Select Grid Size (12 or 24): ");
            gridSize = scanner.nextInt();
            if (gridSize != 12 && gridSize != 24) {
                System.out.println("Invalid choice. Please enter 12 or 24.");
            }
        }

        System.out.println(gridSize);
        return gridSize;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int gridSize = gridSize();
                new PictureMemoryGame(gridSize).setVisible(true);

            }
        });
    }
}