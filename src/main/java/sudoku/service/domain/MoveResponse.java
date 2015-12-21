package sudoku.service.domain;

public class MoveResponse {
    private boolean validMove = false;
    private boolean finished = false;

    public MoveResponse(boolean validMove, boolean finished) {
        this.validMove = validMove;
        this.finished = finished;
    }

    public boolean isValidMove() {
        return validMove;
    }

    public boolean isFinished() {
        return finished;
    }
}
