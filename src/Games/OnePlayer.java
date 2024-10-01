package Games;

import UI.CellButton;
import UI.GameFrame;

import java.util.ArrayList;
import java.util.Random;

public class OnePlayer extends GameFrame {
    boolean starting = true;

    public OnePlayer(int size) {
        super(size);
    }

    @Override
    public boolean onCellClick(CellButton cell) {
        if (!cell.isEmpty()) {
            return false;
        }
        cell.setText("o");
        Random r = new Random();
        boolean diagonalPosReplace = true;
        int diagonalPosSpace = -1;
        boolean diagonalNegReplace = true;
        int diagonalNegSpace = -1;
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
                    if (cells[j][i].isEmpty() && space == -1) {
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

            if (diagonalPosReplace) {
                if (cells[(size-1)-i][i].getChar() != cell.getChar()) {
                    if (cells[(size-1)-i][i].isEmpty() && diagonalPosSpace == -1) {
                        diagonalPosSpace = i;
                    } else {
                        diagonalPosReplace = false;
                    }
                }
            }

            if (diagonalNegReplace) {
                if (cells[i][i].getChar() != cell.getChar()) {
                    if (cells[i][i].isEmpty() && diagonalNegSpace == -1) {
                        diagonalNegSpace = i;
                    } else {
                        diagonalNegReplace = false;
                    }
                }
            }
        }

        if (diagonalPosReplace) {
            cells[(size-1)-diagonalPosSpace][diagonalPosSpace].setText("x");
            return true;
        }
        if (diagonalNegReplace) {
            cells[diagonalNegSpace][diagonalNegSpace].setText("x");
            return true;
        }
        ArrayList<CellButton> emptyCells = getEmptyCells();
        if (!emptyCells.isEmpty()) {
            emptyCells.get(r.nextInt(emptyCells.size())).setText("x");
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        new OnePlayer(3);
    }

}