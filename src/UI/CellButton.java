package UI;

import javax.swing.*;
import java.awt.*;

public class CellButton extends JButton {
    int row;
    int column;
    public CellButton(int row, int column) {
        super();
        this.row = row;
        this.column = column;
        setFont(new Font("Arial", Font.PLAIN, 70));
        setText(" ");
    }

    public char getChar() {
        return getText().charAt(0);
    }

    public char toChar() {
        return getText().charAt(0);
    }
}
