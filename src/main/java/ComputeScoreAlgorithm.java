import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComputeScoreAlgorithm {
    private Board board;
    private Player redPlayer;
    private Player bluePlayer;

    public ComputeScoreAlgorithm(Board board, Player redPlayer, Player bluePlayer) {
        this.board = board;
        this.redPlayer = redPlayer;
        this.bluePlayer = bluePlayer;
    }

    public void computeAndAssignScoreIfPossible(Field coloredField) {
        int redScore = 0;
        int blueScore = 0;
        if (isRowFilled(coloredField)) {
            int rownum = coloredField.getRow();
            List<Field> row = new ArrayList<>(board.getRow(rownum));
            row.sort(Comparator.comparing(Field::getCol));
            redScore += getScoreForPlayer(row, redPlayer);
            blueScore += getScoreForPlayer(row, bluePlayer);
        }
        if (isColumnFilled(coloredField)) {
            int colnum = coloredField.getCol();
            List<Field> column = new ArrayList<>(board.getColumn(colnum));
            column.sort(Comparator.comparing(Field::getRow));

            redScore += getScoreForPlayer(column, redPlayer);
            blueScore += getScoreForPlayer(column, bluePlayer);
        }
        if (isLeftDiagonalFilled(coloredField)) {
            List<Field> diagonal = new ArrayList<>(board.getLeftDiagonal(coloredField));
            diagonal.sort(Comparator.comparing(Field::getCol));


            redScore += getScoreForPlayer(diagonal, redPlayer);
            blueScore += getScoreForPlayer(diagonal, bluePlayer);
        }
        if (isRightDiagonalFilled(coloredField)) {

            List<Field> diagonal = new ArrayList<>(board.getRightDiagonal(coloredField));
            diagonal.sort(Comparator.comparing(Field::getCol));
            redScore += getScoreForPlayer(diagonal, redPlayer);
            blueScore += getScoreForPlayer(diagonal, bluePlayer);
        }

        redPlayer.addToScore(redScore);
        bluePlayer.addToScore(blueScore);
    }

    private int getScoreForPlayer(List<Field> orderedFieldList, Player player) {
        int result = 0;
        int valueToAdd = 0;

        for (Field field : orderedFieldList) {
            if (field.statusToColor() != player.getColor()) {
                if (valueToAdd >= 2) {
                    result += valueToAdd;
                }
                valueToAdd = 0;
            } else {
                valueToAdd += 1;
            }
        }
        if (valueToAdd >= 2) {
            result += valueToAdd;
        }
        return result;
    }

    public boolean isRowFilled(Field coloredField) {
        int rownum = coloredField.getRow();
        return board.getRow(rownum).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY);
    }

    public boolean isColumnFilled(Field coloredField) {
        int colnum = coloredField.getCol();
        return board.getColumn(colnum).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY);
    }

    public boolean isLeftDiagonalFilled(Field coloredField) {
        return board.getLeftDiagonal(coloredField).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY) && board.getLeftDiagonal(coloredField).size() >= 2;
    }

    public boolean isRightDiagonalFilled(Field coloredField) {
        return board.getRightDiagonal(coloredField).stream().allMatch(field -> field.getStatus() != Field.FieldStatus.EMPTY) && board.getRightDiagonal(coloredField).size() >= 2;
    }


}

