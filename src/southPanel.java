import javax.swing.*;


public class southPanel extends JPanel {
    
    private int moveCounter = 0;
    private int seconds = 0;
    JLabel moveCounterLabel = new JLabel("Antal drag: " + moveCounter);
    JLabel timerLabel = new JLabel("Time: 0");
    Timer timer;

    public int getMoveCounter() {
        return moveCounter;
    }

    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }


    southPanel() {

        add(moveCounterLabel);
        add(timerLabel);

        timer = new Timer(1000, e -> {

            seconds++;
            timerLabel.setText("Timer: " + seconds);

        });

    }
}
