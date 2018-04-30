package app;

import java.util.ArrayList;
import java.util.List;

class ComputeScoreAlgorithm {
    private Board board;
    private Player redPlayer;
    private Player bluePlayer;

    ComputeScoreAlgorithm(Board board, Player redPlayer, Player bluePlayer) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.bluePlayer = bluePlayer;
    }

    void computeAndAssignScoreIfPossible(Field coloredField) {
        int redScore = 0;
        int blueScore = 0;
        if (isRowFilled(coloredField)) {
            if (coloredField.getStatus()== Field.FieldStatus.RED){
                redScore += board.getSize();
            }else{
                blueScore += board.getSize();
            }
        }
        if (isColumnFilled(coloredField)) {


            if (coloredField.getStatus()== Field.FieldStatus.RED){
                redScore += board.getSize();
            }else{
                blueScore += board.getSize();
            }
        }
        if (isLeftDiagonalFilled(coloredField)) {
            List<Field> diagonal = new ArrayList<>(board.getLeftDiagonal(coloredField));

            if (coloredField.getStatus()== Field.FieldStatus.RED){
                redScore += diagonal.size();
            }else{
                blueScore += diagonal.size();
            }
        }
        if (isRightDiagonalFilled(coloredField)) {

            List<Field> diagonal = new ArrayList<>(board.getRightDiagonal(coloredField));

            if (coloredField.getStatus()== Field.FieldStatus.RED){
                redScore += diagonal.size();
            }else{
                blueScore += diagonal.size();
            }
        }

        redPlayer.addToScore(redScore);
        bluePlayer.addToScore(blueScore);
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


}

