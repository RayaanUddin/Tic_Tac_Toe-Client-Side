package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class GameFrame extends JFrame {
    final public CellButton[][] cells;
    final public JPanel board;
    final public int size;

    public GameFrame(int size) {
        super();
        if ((size % 2) == 1) {
            new Exception("Size must be an odd number");
        }
        this.size = size;
        // Frame Settings
        setBounds(10, 10, 700, 600);
        setTitle("Naughts & Crosses");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Board Panel
        board = new JPanel();
        board.setLayout(new GridLayout(size, size));
        board.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(board);

        // Cell Buttons
        cells = new CellButton[size][size];
        for (int i = 0; i < size; i++) {
            int tBorder = 1, bBorder = 1;
            if (i == 0) {
                tBorder = 0;
            } else if (i == size - 1) {
                bBorder = 0;
            }
            for (int j = 0; j < size; j++) {
                cells[i][j] = new CellButton(i, j);
                board.add(cells[i][j]);
                int lBorder = 1, rBorder = 1;
                if (j == 0) {
                    lBorder = 0;
                } else if (j == size - 1) {
                    rBorder = 0;
                }
                cells[i][j].setBorder(BorderFactory.createMatteBorder(tBorder, lBorder, bBorder, rBorder, Color.BLACK));
                cells[i][j].addActionListener(e -> {
                    onCellClick((CellButton) e.getSource());
                });
                cells[i][j].addPropertyChangeListener(e -> {
                    if (!e.getPropertyName().equals("text")) {
                        return;
                    }
                    CellButton cell = ((CellButton) e.getSource());
                    if (cell.getText().equals(" ") || cell.getText().isEmpty()) {
                        return;
                    }
                    Character winner = onCellChange(cell);
                    if (winner != null) {
                        win(winner);
                    }
                });
            }
        }
    }

    public void simulateClick(int row, int column) {
        cells[row][column].doClick();
    }

    public Character onCellChange(CellButton cell) {
        final char cell_char = cell.getChar();
        // Horizontal Win
        boolean winner = true;
        for (CellButton c : cells[cell.row]) {
            if (c.getChar() != cell_char) {
                winner = false;
                break;
            }
        }
        if (winner) {
            for (CellButton c : cells[cell.row]) {
                c.setForeground(Color.RED);
            }
            return cell_char;
        }
        // Vertical Win
        winner = true;
        for (int i=0; i<size; i++) {
            if (cells[i][cell.column].getChar() != cell_char) {
                winner = false;
                break;
            }
        }
        if (winner) {
            for (int i=0; i<size; i++) {
                cells[i][cell.column].setForeground(Color.RED);
            }
            return cell_char;
        }

        // Diagonal Win
        if (cells[1][1].getChar() == cell_char) {
            if (cells[0][0].getChar() == cell_char && cells[size-1][size-1].getChar() == cell_char) {
                cells[0][0].setForeground(Color.RED);
                cells[size-1][size-1].setForeground(Color.RED);
                return cell_char;
            } else if (cells[0][size-1].getChar() == cell_char && cells[size-1][0].getChar() == cell_char) {
                cells[0][size-1].setForeground(Color.RED);
                cells[size-1][0].setForeground(Color.RED);
                return cell_char;
            }
        }

        // Draw
        if (getEmptyCells().isEmpty()) {
            return 'd';
        }

        return null;
    }

    public void win(char winner) {
        if (winner == 'd') {
            JOptionPane.showMessageDialog(this, "Draw");
        } else {
            JOptionPane.showMessageDialog(this, "Winner is " + winner);
        }
        for (CellButton[] c1 : cells) {
            for (CellButton c2 : c1) {
                c2.setForeground(Color.BLACK);
                c2.setText(" ");
            }
        }
    }

    public boolean onCellClick(CellButton cell) {
        return false;
    }

    public ArrayList<CellButton> getEmptyCells() {
        ArrayList<CellButton> res = new ArrayList<>();
        for (CellButton[] cellRow : cells) {
            for (CellButton cell : cellRow) {
                if (cell.isEmpty()) {
                    res.add(cell);
                }
            }
        }
        return res;
    }
}
