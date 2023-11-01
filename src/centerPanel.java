import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class centerPanel extends JPanel  {
    
    int rows = 5;
    int colums = rows;
    JButton[][] dimensionArray = new JButton[rows][colums];
    ArrayList<JButton> buttonList = new ArrayList<>();

    public centerPanel() {
        createNewButtons(dimensionArray, buttonList,rows,colums);
    }
    public void initializeButtons(JButton[][] dimensionArray,ArrayList<JButton> buttonList) {

        Collections.shuffle(buttonList);
        for (JButton b : buttonList) {
            add(b);
        }

        for (JButton b : buttonList) {
            if (b.getText().equals(String.valueOf(rows * colums))) {
                b.setText(" ");
                b.setVisible(false);
            }
        }

        int numberOfButtons = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                dimensionArray[i][j] = buttonList.get(numberOfButtons);
                numberOfButtons++;
            }
        }
    }
    public void createNewButtons(JButton[][] dimensionArray,ArrayList<JButton> buttonList,int rows, int colums){
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        setLayout(new GridLayout(rows, colums));

        for (int i = 0; i < (rows * colums); i++) {
            JButton boardNumbers = new JButton(String.valueOf(i + 1));
            boardNumbers.setPreferredSize(new Dimension(80, 80));
            buttonList.add(boardNumbers);
        }

        Collections.shuffle(buttonList);
        for (JButton b:buttonList) {
            add(b);
        }

        for (JButton b:buttonList) {
            if(b.getText().equals(String.valueOf(rows * colums))){
                b.setText(" ");
                b.setVisible(false);
            }
        }

        int numberOfButtons = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                dimensionArray[i][j] = buttonList.get(numberOfButtons);
                numberOfButtons++;
            }
        }
    }


}