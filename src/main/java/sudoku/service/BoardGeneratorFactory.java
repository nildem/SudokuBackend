package sudoku.service;

public class BoardGeneratorFactory {
    public static BoardGenerator getGenerator() {
        try {
            // we use static board only, we can read from property which instance to return
            return (BoardGenerator) Class.forName("sudoku.service.StaticBoardGenerator").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
