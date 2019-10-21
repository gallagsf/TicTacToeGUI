import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.RowSorterEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TicTacToeFrame extends JFrame {
    JLabel titleLabel;

    JPanel mainPanel;

    JPanel mainLabelPanel;

    JPanel gameBoardPanel;

    JPanel buttonPanel;
    JButton quitButton;

    JPanel resultsPanel;
    JLabel XWinsLabel;
    JLabel OWinsLabel;
    JLabel TiesLabel;
    TicTacToe game = new TicTacToe();
    TicTacToeTile[][] board = game.getBoard();

    boolean done = false;
    Scanner in = new Scanner(System.in);

    int Owins = 0;
    int Xwins = 0;
    int ties = 0;

    public TicTacToeFrame() {
        UISetup();
    }

    private void UISetup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);

        Font gameBoardFont = new Font("Helvetica", Font.BOLD, 20);
        Font XOButtons = new Font("Helvetica", Font.BOLD, 30);

        titleLabel = new JLabel("Tic Tac Toe");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 48));
        mainPanel = new JPanel();
        mainLabelPanel = new JPanel();

        gameBoardPanel = new JPanel();

        for (int row = 0; row < game.getBoardSize(); row++) {
            for (int col = 0; col < game.getBoardSize(); col++) {
                gameBoardPanel.add(board[row][col]);
                board[row][col].setFont(XOButtons);
                board[row][col].addActionListener((ActionEvent ae) -> {
                    TicTacToeTile selected = (TicTacToeTile) ae.getSource();
                    game.playTurn(selected.getRow(), selected.getColumn());
                    selected.setForeground(
                            game.getCurrentTurn().name().equals("X") ? Color.BLUE : Color.RED
                    );

                    game.calculateResult();

                    if (game.isOver()) {
                        System.out.println("The game is over and the Result is: " + game.getResult());
                        updateGameResults();
                        Boolean done = SafeInput.getYNConfirmDialog("Play Again?");
                        if (!done) {
                            System.exit(0);
                        }
                        resetGame();
                    }
                });
            }
        }

        buttonPanel = new JPanel();
        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent actionEvent) -> System.exit(0));

        resultsPanel = new JPanel();
        XWinsLabel = new JLabel("X wins: " + Xwins);
        OWinsLabel = new JLabel("O wins: " + Owins);
        TiesLabel = new JLabel("Ties: " + ties);

        add(mainPanel);
        setTitle("Tic Tac Toe");

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mainLabelPanel, BorderLayout.NORTH);
        mainLabelPanel.add(titleLabel);

        mainPanel.add(gameBoardPanel, BorderLayout.CENTER);
        gameBoardPanel.setLayout(new GridLayout(3, 3));


        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(quitButton);

        mainPanel.add(resultsPanel, BorderLayout.EAST);
        resultsPanel.setLayout(new GridLayout(3, 1));
        resultsPanel.add(XWinsLabel);
        resultsPanel.add(OWinsLabel);
        resultsPanel.add(TiesLabel);

        setVisible(true);
    }

    private void resetGame() {
        for (int row = 0; row < game.getBoardSize(); row++) {
            for (int col = 0; col < game.getBoardSize(); col++) {
                board[row][col].setValue(" ");
            }
        }
    }

    private void updateGameResults() {
        if (game.getResult() == "X") {
            Xwins++;
            XWinsLabel.setText("X wins: " + Xwins);
        } else if (game.getResult() == "O") {
            Owins++;
            OWinsLabel.setText("O wins: " + Owins);
        } else {
            ties++;
            TiesLabel.setText("Ties: " + ties);
        }
    }
}