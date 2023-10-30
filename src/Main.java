import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new Main();
    }

    northPanel northPanel = new northPanel();
    centerPanel centerPanel = new centerPanel();
    southPanel southPanel = new southPanel();


    Main() {
        setTitle("Game Of Fifteen");
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);

        northPanel.newGameButton.addActionListener(this);
        northPanel.changeColorOnGameButton.addActionListener(this);
        northPanel.changeColorOnNumbersButton.addActionListener(this);

        southPanel.timer.start();

        centerPanel.dimensionArray[0][0].addActionListener(this);
        centerPanel.dimensionArray[0][1].addActionListener(this);
        centerPanel.dimensionArray[0][2].addActionListener(this);
        centerPanel.dimensionArray[0][3].addActionListener(this);
        centerPanel.dimensionArray[1][0].addActionListener(this);
        centerPanel.dimensionArray[1][1].addActionListener(this);
        centerPanel.dimensionArray[1][2].addActionListener(this);
        centerPanel.dimensionArray[1][3].addActionListener(this);
        centerPanel.dimensionArray[2][0].addActionListener(this);
        centerPanel.dimensionArray[2][1].addActionListener(this);
        centerPanel.dimensionArray[2][2].addActionListener(this);
        centerPanel.dimensionArray[2][3].addActionListener(this);
        centerPanel.dimensionArray[3][0].addActionListener(this);
        centerPanel.dimensionArray[3][1].addActionListener(this);
        centerPanel.dimensionArray[3][2].addActionListener(this);
        centerPanel.dimensionArray[3][3].addActionListener(this);


        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    

    public void updateMoveCounter() {
        southPanel.moveCounter++;
        southPanel.moveCounterLabel.setText("Antal drag: " + southPanel.moveCounter);
    }

    public void shuffleGame() {
        int gridSize = centerPanel.dimensionArray.length;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                centerPanel.buttonList.add(centerPanel.dimensionArray[i][j]);
            }
        }
        Collections.shuffle(centerPanel.buttonList);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                centerPanel.dimensionArray[i][j].add(centerPanel.buttonList.get((j + 1) + (i * gridSize)));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoveOk(buttonPosition(e.getActionCommand(), centerPanel.dimensionArray))) {
            int[] clickedButtonPosition = convertStringToIntArray(buttonPosition(e.getActionCommand(), centerPanel.dimensionArray));
            move1(clickedButtonPosition[0], clickedButtonPosition[1]);
            updateMoveCounter();
            if (checkIfWin.checkIfWinner2(centerPanel.dimensionArray)) {
                System.out.println("du vann");
            }
        }
        if (e.getSource() == northPanel.newGameButton) {
            centerPanel.initializeButtons2(centerPanel.dimensionArray, centerPanel.buttonList);
            southPanel.seconds = 0;
            southPanel.moveCounter = 0;
        }
        
        if (e.getSource() == northPanel.changeColorOnNumbersButton) {
            Color colorSelectorNumbers = JColorChooser.showDialog(null, "Välj en färg på spelbrickorna", Color.WHITE);
            if (colorSelectorNumbers != null) {
                Component[] comps = centerPanel.getComponents();
                for (Component comp : comps) {
                    if (comp instanceof JButton b) {
                        b.setBackground(colorSelectorNumbers);
                    }
                }
            }
        }
        if (e.getSource() == northPanel.changeColorOnGameButton) {
            Color colorSelectorGame = JColorChooser.showDialog(null, "Välj en färg på spelplanen", Color.BLACK);
            if (colorSelectorGame != null) {
                centerPanel.setBackground(colorSelectorGame);
                northPanel.setBackground(colorSelectorGame);
            }
        }
    }

    public String buttonPosition(String buttonNumber, JButton[][] dimensionArray) {
        String searchString = buttonNumber;

        int rowPosition = -1;
        int colPosition = -1;

        for (int i = 0; i < dimensionArray.length; i++) {
            for (int j = 0; j < dimensionArray[i].length; j++) {
                if (dimensionArray[i][j].getText().equals(searchString)) {
                    rowPosition = i;
                    colPosition = j;
                    break;
                }
            }
            if (rowPosition != -1) {
                break;
            }
        }
        return rowPosition + ":" + colPosition;
    }

    public boolean isMoveOk(String clickedButtonPosition) {
        boolean returnboolean = false;

        int[] clickedbuttonPositionParts = convertStringToIntArray(clickedButtonPosition);
        int[] blankButtonPosition = convertStringToIntArray(buttonPosition(" ", centerPanel.dimensionArray));
        if (clickedbuttonPositionParts[0] == (blankButtonPosition[0]) &&
                (blankButtonPosition[1] == clickedbuttonPositionParts[1] + 1 ||
                        blankButtonPosition[1] == clickedbuttonPositionParts[1] - 1)) {
            returnboolean = true;
        } else if (clickedbuttonPositionParts[1] == (blankButtonPosition[1]) &&
                (blankButtonPosition[0] == clickedbuttonPositionParts[0] + 1 ||
                        blankButtonPosition[0] == clickedbuttonPositionParts[0] - 1)) {
            returnboolean = true;
        }
        return returnboolean;
    }

    public static int[] convertStringToIntArray(String input) {
        String[] parts = input.split(":");
        int[] intArray = new int[parts.length];

        for (int i = 0; i < parts.length; i++) {
            intArray[i] = Integer.parseInt(parts[i]);
        }

        return intArray;
    }

    public void move1(int a, int b) {
        int[] blankButtonPosition = convertStringToIntArray(buttonPosition(" ", centerPanel.dimensionArray));
        centerPanel.dimensionArray[blankButtonPosition[0]][blankButtonPosition[1]].setText(centerPanel.dimensionArray[a][b].getText());
        centerPanel.dimensionArray[a][b].setText(" ");
        centerPanel.dimensionArray[a][b].setVisible(false);
        centerPanel.dimensionArray[blankButtonPosition[0]][blankButtonPosition[1]].setVisible(true);
    }
}
