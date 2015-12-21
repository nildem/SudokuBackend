package sudoku.service.domain;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sudoku.service.BoardGeneratorFactory;

@Component
public class Board {
    private int[][] board;
    public static final int SIZE = 9;
    public static final int SQUARESIZE = 3;

    public Board() {
        board = new int[SIZE][SIZE];
    }

    public int getValue(int i, int j) {
        return board[i][j];
    }

    public boolean setValue(int i, int j, int value) {
        System.out.println("Set [" + i + ":" + j + "] = " + value);
        if (value < 0 || value > 9)
            return false;
        board[i][j] = value;
        System.out.println(toString());
        return true;
    }

    @Override
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                buf.append(board[i][j] + " ");
            }
            buf.append("\n");
        }
        return buf.toString();
    }

    /* Check if puzzle is solved*/
    public boolean isFinished() {
        for (int i = 0; i < SIZE; i++) {
            int[] row = new int[SIZE];
            int[] square = new int[SIZE];
            int[] column = board[i].clone();
            for (int j = 0; j < SIZE; j++) {
                row[j] = board[j][i];
                square[j] = board[(i / SQUARESIZE) * SQUARESIZE + j / SQUARESIZE][i * SQUARESIZE % SIZE + j
                        % SQUARESIZE];
            }
            if (!(validate(column) && validate(row) && validate(square)))
                return false;
        }
        return true;
    }

    private boolean validate(int[] check) {
        int i = 0;
        Arrays.sort(check);
        for (int number : check) {
            if (number != ++i)
                return false;
        }
        return true;
    }

    /*Load board with initial data. Use factory how to load board.*/
    @PostConstruct
    public void loadInitialBoard() {
        board = BoardGeneratorFactory.getGenerator().generate();
    }

    public void loadBoard(int[][] input) {
        board = input;
    }
}
