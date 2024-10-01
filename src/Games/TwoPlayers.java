package Games;

import UI.CellButton;
import UI.GameFrame;

public class TwoPlayers extends GameFrame {
    char turn = 'o';

    public TwoPlayers(int size) {
        super(size);
    }

    @Override
    public boolean onCellClick(CellButton cell) {
        if (!cell.isEmpty()) {
            return false;
        }
        cell.setText(Character.toString(turn));
        switch (turn) {
            case 'o': turn = 'x'; break;
            case 'x': turn = 'o'; break;
        }
        return true;
    }

    public static void main(String[] args) {
        new TwoPlayers(3);
    }

}