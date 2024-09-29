package Games;

import UI.CellButton;
import UI.GameFrame;

import java.util.Random;

public class OnePlayer extends GameFrame {
    boolean starting = true;

    public OnePlayer(int size) {
        super(size);
    }

    @Override
    public boolean onCellClick(CellButton cell) {
        System.out.println("clicked");
        if (cell.getChar() != ' ') {
            return false;
        }
        cell.setText("o");
        Random r = new Random();
        for (int i=0; i<size; i++) {
            int space = -1;
            boolean replace = true;
            for (int j=0; j<size; j++) {
                if (cells[i][j].getChar() != cell.getChar()) {
                    if (cells[i][j].getChar() == ' ' && space == -1) {
                        space = j;
                    } else {
                        replace = false;
                        break;
                    }
                }
            }
            if (replace && (space != -1)) {
                cells[i][space].setText("x");
                return true;
            }

            space = -1;
            replace = true;
            for (int j=0; j<size; j++) {
                if (cells[j][i].getChar() != cell.getChar()) {
                    if (cells[j][i].getChar() == ' ' && space == -1) {
                        space = j;
                    } else {
                        replace = false;
                        break;
                    }
                }
            }
            if (replace && (space != -1)) {
                cells[space][i].setText("x");
                return true;
            }
        }
        CellButton mark;
        do {
            boolean nospace = true;
            for (CellButton[] cr : cells) {
                for (CellButton c: cr) {
                    if (c.getChar() == ' ') {
                        nospace = false;
                        break;
                    }
                }
            }
            mark = cells[r.nextInt(size)][r.nextInt(size)];
        } while (mark.getChar() != ' ');
        mark.setText("x");
        return true;
    }
    
    public static void main(String[] args) {
        new OnePlayer(3);
    }

}