package sudoku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sudoku.service.domain.Board;
import sudoku.service.domain.MoveResponse;

@RestController
public class SudokuController {
    @Autowired
    private Board board;

    @RequestMapping("/move")
    public MoveResponse move(@RequestParam(value = "x", defaultValue = "0") String x,
            @RequestParam(value = "y", defaultValue = "0") String y,
            @RequestParam(value = "value", defaultValue = "0") String value) {
        return new MoveResponse(board.setValue(Integer.valueOf(x), Integer.valueOf(y), Integer.valueOf(value)),
                board.isFinished());
    }
}
