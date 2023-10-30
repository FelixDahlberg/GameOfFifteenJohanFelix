import javax.swing.*;
import java.util.ArrayList;

public class checkIfWin {



    public static void main(String[] args) {
        centerPanel c = new centerPanel();
        JButton button1= new JButton("1");
        JButton button2= new JButton("2");
        JButton button3= new JButton("3");
        JButton button4= new JButton("4");
        JButton button5= new JButton("5");
        JButton button6= new JButton("6");
        JButton button7= new JButton("7");
        JButton button8= new JButton("8");
        JButton button9= new JButton(" ");
        JButton[] list1 = new JButton[]{button1,button2,button3};
        JButton[] list2 = new JButton[]{button4,button5,button6};
        JButton[] list3 = new JButton[]{button7,button8,button9};
        JButton[][] grid = new JButton[][]{list1,list2,list3};

        System.out.println(checkIfWinner2(grid));

    }
    public static boolean checkIfWinner2(JButton[][] dimensionArray) {
        JButton[][] dimensionArrayCopy = copyJButtonArray(dimensionArray);
        boolean returnBoolean = false;
        int gridSize = dimensionArrayCopy.length;
        StringBuilder actual = new StringBuilder();
        StringBuilder check = new StringBuilder();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (dimensionArrayCopy[i][j].getText().equals(" ")) {
                    dimensionArrayCopy[i][j].setText(String.valueOf(gridSize * gridSize));
                }
                int a = (j + 1) + (i * gridSize);
                actual.append(dimensionArrayCopy[i][j].getText());
                check.append(a);
            }
        }
        if (actual.toString().equals(check.toString())) {
            returnBoolean = true;
        }

        System.out.println("Actual: " + actual);
        System.out.println("Check: " + check);
        return returnBoolean;
    }
    public static JButton[][] copyJButtonArray(JButton[][] originalArray) {
        int rows = originalArray.length;
        int columns = originalArray[0].length;

        JButton[][] newArray = new JButton[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newArray[i][j] = new JButton(originalArray[i][j].getText()); // You can also copy other properties if needed
            }
        }

        return newArray;
    }

}
