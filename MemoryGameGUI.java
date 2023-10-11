import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MemoryGameGUI extends JFrame {
    private ArrayList<String> cardSymbols;
    private ArrayList<String> cards;
    private JButton[] cardButtons;
    private int numberOfMatches;
    private int firstCardIndex;
    private int secondCardIndex;

    public int gridSize;

    public MemoryGameGUI() {
        setTitle("Memory Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardSymbols = new ArrayList<>();
        cardSymbols.add("A");
        cardSymbols.add("B");
        cardSymbols.add("C");
        cardSymbols.add("D");
        cardSymbols.add("E");
        cardSymbols.add("F");
        cardSymbols.add("G");
        cardSymbols.add("H");

        // Duplicate symbols to create matching pairs
        cards = new ArrayList<>(cardSymbols);
        cards.addAll(cardSymbols);

        Collections.shuffle(cards);

        int numRows;
        int numCols;

        if (gridSize == 12) {
            numRows = 4;
            numCols = 3;
        } else if (gridSize == 24) {
            numRows = 6;
            numCols = 4;
        } else {
            numRows = 4;
            numCols = 3;
        }

        JPanel cardPanel = new JPanel(new GridLayout(numRows, numCols));


        //We add an action listener to each button to handle card clicks.
        for (int i = 0; i < cardButtons.length; i++) {
            final int index = i;
            cardButtons[i] = new JButton("?");
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
    private static int gridSize() {
        Scanner scanner = new Scanner(System.in);
        int gridSize = 0;

        while (gridSize != 12 && gridSize != 24) {
            System.out.println("Select Grid Size (12 or 24): ");
            gridSize = scanner.nextInt();
            if (gridSize != 12 && gridSize != 24) {
                System.out.println("Invalid choice. Please enter 12 or 24.");
            }
        }

        return gridSize;
    }
    public MemoryGameGUI(int gridSize){
        setTitle("Memory Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardSymbols = new ArrayList<>();
        cardSymbols.add("A");
        cardSymbols.add("B");
        cardSymbols.add("C");
        cardSymbols.add("D");
        cardSymbols.add("E");
        cardSymbols.add("F");
        cardSymbols.add("G");
        cardSymbols.add("H");

        // Duplicate symbols to create matching pairs
        cards = new ArrayList<>(cardSymbols);
        cards.addAll(cardSymbols);

        Collections.shuffle(cards);

        JPanel cardPanel2 = new JPanel(new GridLayout(6,4));
        JPanel cardPanel = new JPanel(new GridLayout(4, 3));
        this.gridSize = gridSize;
        this.cardButtons = new JButton[12];
    }
    //Handle card clicks.
    private void handleCardClick(int index) {
        if (cardButtons[index].getText().equals("?")) {
            cardButtons[index].setText(cards.get(index));
            if (firstCardIndex == -1) {
                firstCardIndex = index;
            } else {
                secondCardIndex = index;
                if (cards.get(firstCardIndex).equals(cards.get(secondCardIndex))) {
                    cardButtons[firstCardIndex].setEnabled(false);
                    cardButtons[secondCardIndex].setEnabled(false);
                    numberOfMatches++;
                    //When all pairs have been matched, we display a congratulatory message.
                    if (numberOfMatches == cardSymbols.size()) {
                        JOptionPane.showMessageDialog(null, "Congratulations! You've won!");
                        System.exit(0);
                    }
                } else {
                    Timer timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            cardButtons[firstCardIndex].setText("?");
                            cardButtons[secondCardIndex].setText("?");
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
                firstCardIndex = -1;
            }
        }
    }

    //The main method sets up the GUI in the Event Dispatch Thread (EDT).
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int gridSize = gridSize();
                new MemoryGameGUI(gridSize).setVisible(true);
            }
        });
    }
}