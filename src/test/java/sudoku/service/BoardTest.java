package sudoku.service;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import sudoku.service.domain.Board;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = { Board.class })
public class BoardTest {
    @Autowired
    private Board board;
    @Autowired
    private Board finishedBoard;
    //solution of given sudoku puzzle
    private static int[][] input = new int[][] { { 7, 9, 2, 1, 4, 6, 5, 3, 8 }, //
            { 4, 6, 5, 2, 3, 8, 7, 1, 9 },//
            { 3, 1, 8, 5, 7, 9, 6, 4, 2 },//
            { 5, 3, 9, 8, 6, 4, 2, 7, 1 },//
            { 2, 7, 6, 9, 1, 3, 4, 8, 5 },//
            { 8, 4, 1, 7, 2, 5, 9, 6, 3 },//
            { 9, 5, 7, 4, 8, 1, 3, 2, 6 },//
            { 1, 2, 3, 6, 5, 7, 8, 9, 4 },//
            { 6, 8, 4, 3, 9, 2, 1, 5, 7 } };

    @Before
    public void initBoard() {
        board.loadInitialBoard();
    }

    @Test
    public void validMove() {
        //check
        boolean validMove = board.setValue(1, 1, 2);
        Assert.assertEquals(true, validMove);
        Assert.assertEquals(2, board.getValue(1, 1));
        // uncheck, also valid
        validMove = board.setValue(1, 1, 0);
        Assert.assertEquals(true, validMove);
        Assert.assertEquals(0, board.getValue(1, 1));
    }

    @Test
    public void invalidMove() {
        String initialBoard = board.toString();
        boolean validMove = board.setValue(1, 1, 10);
        Assert.assertEquals(false, validMove);
        Assert.assertEquals(initialBoard, board.toString());
        validMove = board.setValue(1, 1, -1);
        Assert.assertEquals(false, validMove);
        Assert.assertEquals(initialBoard, board.toString());
    }

    @Test
    public void notFinished() {
        //initial board
        Assert.assertEquals(false, board.isFinished());
        //initial board + make one move
        board.setValue(1, 1, 2);
        Assert.assertEquals(false, board.isFinished());
    }

    @Test
    public void notFinishedZeroBoard() {
        // zero board
        int[][] array = new int[9][9];
        for (int[] row : array)
            Arrays.fill(row, 0);
        Board zeroBoard = new Board();
        zeroBoard.loadBoard(array);
        Assert.assertEquals(false, board.isFinished());
    }

    @Test
    public void finished() {
        finishedBoard.loadBoard(input);
        boolean validMove = false;
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                validMove = board.setValue(i, j, finishedBoard.getValue(i, j));
                Assert.assertEquals(true, validMove);
            }
        }
        Assert.assertEquals(true, board.isFinished());
    }
}
