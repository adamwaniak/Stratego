package app.AIAlgorithms;

import app.Board;
import app.Field;

class CloseLineAlgorithm {
    private RandomAlgorithm randomAlgorithmAlgorithm;
    private Board board;

    CloseLineAlgorithm(Board board) {
        this.board = board;
        randomAlgorithmAlgorithm = new RandomAlgorithm(board);
    }

    Field closeLineOrRandom() {
        int size = board.getSize();
        Field field = getFieldToClosure(size);
        if (field == null) {
            field = randomAlgorithmAlgorithm.makeRandomMove();
        }
        return field;
    }

    private Field getFieldToClosure(int size) {
        for (int i = 0; i < size; i++) {
            if (board.getRow(i).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).count() == 1) {
                return board.getRow(i).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).findFirst().get();
            }
            if (board.getColumn(i).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).count() == 1) {
                return board.getColumn(i).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).findFirst().get();
            }
            if (board.getLeftDiagonal(board.getField(i, 0)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).count() == 1) {
                return board.getLeftDiagonal(board.getField(i, 0)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).findFirst().get();
            }
            if (board.getLeftDiagonal(board.getField(size - 1, i)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).count() == 1) {
                return board.getLeftDiagonal(board.getField(size - 1, i)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).findFirst().get();
            }

            if (board.getRightDiagonal(board.getField(i, 0)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).count() == 1) {
                return board.getRightDiagonal(board.getField(i, 0)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).findFirst().get();
            }
            if (board.getRightDiagonal(board.getField(0, i)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).count() == 1) {
                return board.getRightDiagonal(board.getField(0, i)).stream().filter(f -> f.getStatus() == Field.FieldStatus.EMPTY).findFirst().get();
            }
        }
        return null;
    }
}
