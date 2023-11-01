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
        northPanel.changeSizeOnGame.addActionListener(this);
        northPanel.changeColorOnGameButton.addActionListener(this);
        northPanel.changeColorOnNumbersButton.addActionListener(this);

        southPanel.timer.start();
        addActionlistenerToArrayButtons(centerPanel.dimensionArray);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateMoveCounter() {
        southPanel.setMoveCounter(southPanel.getMoveCounter() + 1);
        southPanel.moveCounterLabel.setText("Antal drag: " + southPanel.getMoveCounter());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isMoveOk(buttonPosition(e.getActionCommand(), centerPanel.dimensionArray))){
            int[] clickedButtonPosition = convertStringToIntArray(buttonPosition(e.getActionCommand(), centerPanel.dimensionArray));
            moveButton(clickedButtonPosition[0],clickedButtonPosition[1]);
            updateMoveCounter();
            if (checkIfWin.checkIfWinner(centerPanel.dimensionArray)){
                JOptionPane.showMessageDialog(null, "Grattis, du vann!\n" +
                        " Du gjorde det på " + southPanel.getMoveCounter() + " drag och " + southPanel.getSeconds() + " sekunder");
            }
        }
        if (e.getSource() == northPanel.newGameButton) {
            centerPanel.initializeButtons(centerPanel.dimensionArray, centerPanel.buttonList);
            southPanel.setSeconds(0);
            southPanel.setMoveCounter(0);
        }
        
        if (e.getSource() == northPanel.changeSizeOnGame){
            try {
                int gameSize = Integer.parseInt(JOptionPane.showInputDialog("Hur stor plan vill du spela?"));
                if (gameSize < 1){
                    JOptionPane.showMessageDialog(null,"siffran måste vara 2 eller större");
                }
                centerPanel.rows = gameSize;
                centerPanel.colums = gameSize;
                centerPanel.dimensionArray = new JButton[gameSize][gameSize];
                centerPanel.buttonList = new ArrayList<>();
                centerPanel.removeAll();
                centerPanel.createNewButtons(centerPanel.dimensionArray, centerPanel.buttonList,gameSize,gameSize);
                addActionlistenerToArrayButtons(centerPanel.dimensionArray);
                southPanel.setSeconds(0);
                southPanel.setMoveCounter(0);
            }catch (NumberFormatException a){
                JOptionPane.showMessageDialog(null,"Måste vara en siffra");
                a.printStackTrace();
            }
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
                blankButtonPosition[1] == clickedbuttonPositionParts[1] - 1 )) {
            returnboolean = true;
        } else if (clickedbuttonPositionParts[1] == (blankButtonPosition[1]) &&
                (blankButtonPosition[0] == clickedbuttonPositionParts[0] + 1 ||
                blankButtonPosition[0] == clickedbuttonPositionParts[0] - 1 )) {
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
    public void moveButton(int a, int b){
        int[] blankButtonPosition = convertStringToIntArray(buttonPosition(" ", centerPanel.dimensionArray));
        centerPanel.dimensionArray[blankButtonPosition[0]][blankButtonPosition[1]].setText(centerPanel.dimensionArray[a][b].getText());
        centerPanel.dimensionArray[a][b].setText(" ");
        centerPanel.dimensionArray[a][b].setVisible(false);
        centerPanel.dimensionArray[blankButtonPosition[0]][blankButtonPosition[1]].setVisible(true);
    }
    public void addActionlistenerToArrayButtons(JButton[][] dimensionArray){
        int gridSize = dimensionArray.length;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                dimensionArray[i][j].addActionListener(this);
            }
        }
    }
}
