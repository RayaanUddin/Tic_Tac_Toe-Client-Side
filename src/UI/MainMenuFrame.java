package UI;
import javax.swing.*;
import java.awt.*;

public class MainMenuFrame extends JFrame {
    public MainMenuFrame() {
        super();
        setLayout(new GridLayout(3,1));
        JButton onePlayerButton = new JButton("Player vs CPU");
        JButton twoPlayerButton = new JButton("Player vs Player");
        JButton onlineButton = new JButton("Player vs Online");
        add(onePlayerButton);
        add(twoPlayerButton);
        add(onlineButton);
        onePlayerButton.addActionListener(e -> {

        });
        twoPlayerButton.addActionListener(e -> {

        });
        onlineButton.addActionListener(e -> {

        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        setVisible(true);
    }

    public static void main(String[] arg) {
        new MainMenuFrame();

    }
}
