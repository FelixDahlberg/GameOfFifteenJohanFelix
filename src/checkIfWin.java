import javax.swing.*;
import java.util.ArrayList;

public class checkIfWin {
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
        if (actual.toString().contentEquals(check)) {
            returnBoolean = true;
        }
        return returnBoolean;
    }
    public static JButton[][] copyJButtonArray(JButton[][] originalArray) {
        int rows = originalArray.length;
        int columns = originalArray[0].length;

        JButton[][] newArray = new JButton[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newArray[i][j] = new JButton(originalArray[i][j].getText());
            }
        }

        return newArray;
    }

}
