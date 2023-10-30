import javax.swing.*;
import java.util.ArrayList;

public class checkIfWin {



    public static void main(String[] args) {
        centerPanel c = new centerPanel();
        JButton button1= new JButton(" ");
        JButton button2= new JButton("2");
        JButton button3= new JButton("3");
        JButton button4= new JButton("4");
        JButton button5= new JButton("5");
        JButton button6= new JButton("6");
        JButton button7= new JButton("7");
        JButton button8= new JButton("9");
        JButton button9= new JButton("8");
        JButton[] list1 = new JButton[]{button1,button2,button3};
        JButton[] list2 = new JButton[]{button4,button5,button6};
        JButton[] list3 = new JButton[]{button7,button8,button9};
        JButton[][] grid = new JButton[][]{list1,list2,list3};

        System.out.println(checkIfWinner2(grid));

    }
    public static boolean checkIfWinner1(JButton[][] dimensionArray){
        boolean returnBoolean = false;
        int gridSize = dimensionArray.length;
        StringBuilder actual = new StringBuilder();
        StringBuilder check = new StringBuilder();

        for (int i = 0; i < dimensionArray.length; i++) {
            gridSize = (gridSize * i) + 1;
            for (int j = 0; j < dimensionArray[i].length; j++) {
                if (dimensionArray[i][j].getText().equals(" ")){
                    dimensionArray[i][j].setText("1");
                }
                    int a = (j + 1) + (i * dimensionArray.length);
                    actual.append(dimensionArray[i][j].getText());
                    check.append(a);
            }
        }
        if (actual.toString().equalsIgnoreCase(check.toString())) {
            returnBoolean = true;
        }
        //System.out.println(actual);
        //System.out.println(check);
        return returnBoolean;
    }
    public static boolean checkIfWinner2(JButton[][] dimensionArray) {
        boolean returnBoolean = false;
        int gridSize = dimensionArray.length;
        StringBuilder actual = new StringBuilder();
        StringBuilder check = new StringBuilder();

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (dimensionArray[i][j].getText().equals(" ")) {
                    dimensionArray[i][j].setText("1");
                }
                int a = (j + 1) + (i * gridSize);
                actual.append(dimensionArray[i][j].getText());
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
    static public boolean checkIfWinner(ArrayList<ArrayList<Integer>> inputArrayGrid){
        boolean returnBoolean = false;
        int gridSize = inputArrayGrid.size();
        StringBuilder actual = new StringBuilder();
        StringBuilder check = new StringBuilder();
        for (int i = 0; i < inputArrayGrid.size(); i++) {
            gridSize = (gridSize * i) + 1;
            for (int j = 0; j < inputArrayGrid.get(i).size(); j++) {
                int a = (j + 1) + (i * inputArrayGrid.size());
                actual.append(inputArrayGrid.get(i).get(j));
                check.append(a);
            }
        }
        if (actual.toString().equalsIgnoreCase(check.toString())) {
            returnBoolean = true;
        }
        return returnBoolean;
    }
}
