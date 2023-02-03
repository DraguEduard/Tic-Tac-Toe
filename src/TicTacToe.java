import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

//Aplicatie creata de Dragu Eduard//
public class TicTacToe {
    private static final int ROWS = 3;
    private static final int COLS = 3;

    private JFrame frame;
    private JPanel panel;
    private JButton[][] buttons;

    private boolean crossTurn;
    private boolean vsAI;

    public TicTacToe() {
        // Ask the user if they want to play against AI or another player
        int choice = JOptionPane.showOptionDialog(null, "Choose game mode", "Tic Tac Toe", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Against AI", "Against player"}, "Against AI");
        vsAI = choice == JOptionPane.YES_OPTION;

        frame = new JFrame("Tic Tac Toe");
        panel = new JPanel(new GridLayout(ROWS, COLS));
        buttons = new JButton[ROWS][COLS];

        crossTurn = true;

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JButton button = (JButton) e.getSource();
                        button.setText(crossTurn ? "X" : "O");
                        button.setEnabled(false);
                        crossTurn = !crossTurn;
                        if (vsAI && !crossTurn) {
                            nextMove();
                        }
                    }
                });
                buttons[row][col] = button;
                panel.add(button);
            }
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(panel);
        frame.setSize(750, 750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void nextMove() {
        int row, col;
        do {
            row = new Random().nextInt(ROWS);
            col = new Random().nextInt(COLS);
        } while (!buttons[row][col].getText().isEmpty());
        buttons[row][col].setText("O");
        buttons[row][col].setEnabled(false);
        crossTurn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
