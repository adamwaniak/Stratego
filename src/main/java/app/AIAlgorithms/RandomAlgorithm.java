package app.AIAlgorithms;

import app.Board;
import app.Field;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class RandomAlgorithm {
    private Board board;

    RandomAlgorithm(Board board) {
        this.board = board;
    }

    Field makeRandomMove() {
        List<Field> possibleFields = board.getFields().stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).collect(Collectors.toList());
        Collections.shuffle(possibleFields);
        return possibleFields.get(0);
    }
}
