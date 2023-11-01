import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class northPanel extends JPanel implements ActionListener {

    JButton newGameButton = new JButton("New Game");
    JButton changeSizeOnGame = new JButton("Byt storlek på spelplanen");
    JButton changeColorOnNumbersButton = new JButton("Byt färg på spelbrickorna");
    JButton changeColorOnGameButton = new JButton("Byt färg på spelaplanen");

    JButton exitButton = new JButton("Exit");

    northPanel() {
        add(newGameButton);
        add(changeSizeOnGame);
        add(changeColorOnNumbersButton);
        add(changeColorOnGameButton);
        add(exitButton);
        exitButton.addActionListener(this);
        newGameButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }
}
