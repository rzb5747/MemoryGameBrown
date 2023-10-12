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
    private int firstCardIndex;
    private int secondCardIndex;

    public PictureMemoryGame(int gridSize) {
        setTitle("Picture Memory Game");
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        imagePaths = new ArrayList<>();
        // Add file paths to your images here

        imagePaths.add("balloon1.png");
        imagePaths.add("balloon2.png");
        imagePaths.add("balloon4.png");
        imagePaths.add("balloon5.png");
        imagePaths.add("balloon6.png");
        imagePaths.add("balloon7.png");
        imagePaths.add("balloon8.png");
        imagePaths.add("balloon9.png");
        imagePaths.add("balloon10.png");
        imagePaths.add("balloon11.png");
        imagePaths.add("balloon12.png");
        imagePaths.add("balloon13.png");
        imagePaths.add("balloon14.png");




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
        imagePaths.add("balloon13.png");
        imagePaths.add("balloon14.png");

        cardImages = new ArrayList<>();
        for (String imagePath : imagePaths) {
            cardImages.add("");
        }

        Collections.shuffle(imagePaths);
        Collections.shuffle(cardImages);

        int numRows = 0;
        int numCols = 0;

        if (gridSize == 12) {
            numRows = 4;
            numCols = 3;
        } else if (gridSize == 24) {
            numRows = 6;
            numCols = 4;
        } else {
            System.out.println("Invalid entry, please enter 12 or 24.");
        }
        JPanel cardPanel = new JPanel(new GridLayout(numRows, numCols));

        cardButtons = new JButton[gridSize];

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

        add(cardPanel);
    }

    private void handleCardClick(int index) {
        if (cardButtons[index].getIcon() == null) {
            return; // Already matched card, do nothing
        }

        if (firstCardIndex == -1) {
            firstCardIndex = index;
            cardButtons[firstCardIndex].setIcon(new ImageIcon(imagePaths.get(index)));
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
                            JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
                            System.exit(0);
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