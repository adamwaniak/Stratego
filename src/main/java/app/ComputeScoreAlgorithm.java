package app;

import java.util.ArrayList;
import java.util.List;

public class ComputeScoreAlgorithm {
    private Board board;
    private Player redPlayer;
    private Player bluePlayer;

    ComputeScoreAlgorithm(Board board, Player redPlayer, Player bluePlayer) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.bluePlayer = bluePlayer;
    }

    public ComputeScoreAlgorithm() {
    }

    public void computeAndAssignScoreIfPossible(Field coloredField) {
        int score = computeScore(coloredField);
        if (coloredField.getStatus() == Field.FieldStatus.RED) {
            redPlayer.addToScore(score);

        } else {
            bluePlayer.addToScore(score);
        }
    }


    public int computeScore(Field coloredField) {
        int score = 0;
        if (isRowFilled(coloredField)) {
            score += board.getSize();
        }
        if (isColumnFilled(coloredField)) {
            score += board.getSize();
        }
        if (isLeftDiagonalFilled(coloredField)) {
            List<Field> diagonal = new ArrayList<>(board.getLeftDiagonal(coloredField));
            score += diagonal.size();
        }
        if (isRightDiagonalFilled(coloredField)) {
            List<Field> diagonal = new ArrayList<>(board.getRightDiagonal(coloredField));
            score += diagonal.size();
        }
        return score;
    }

    private boolean isRowFilled(Field coloredField) {
        int rownum = coloredField.getRow();
        return board.getRow(rownum).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY);
    }

    private boolean isColumnFilled(Field coloredField) {
        int colnum = coloredField.getCol();
        return board.getColumn(colnum).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY);
    }

    private boolean isLeftDiagonalFilled(Field coloredField) {
        return board.getLeftDiagonal(coloredField).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY) && board.getLeftDiagonal(coloredField).size() >= 2;
    }

    private boolean isRightDiagonalFilled(Field coloredField) {
        return board.getRightDiagonal(coloredField).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY) && board.getRightDiagonal(coloredField).size() >= 2;
    }

    public ComputeScoreAlgorithm setBoard(Board board) {
        this.board = board;
        return this;
    }
}

