package app.AIAlgorithms;

import app.Board;
import app.ComputeScoreAlgorithm;
import app.Field;

import java.util.List;

public class MinMaxAlgorithm {
    public static final int MAX_DEPTH = 3;
    private Board board;
    private ComputeScoreAlgorithm computeScoreAlgorithm;

    public MinMaxAlgorithm(Board board) {
        this.board = board;
        computeScoreAlgorithm = new ComputeScoreAlgorithm();
        computeScoreAlgorithm.setBoard(board);
    }

    public Field getComputeField(int maxDepth) {
        return minmax(maxDepth);
    }

    private Field minmax(int maxDepth) {
        Field bestField = null;
        int maxValue = Integer.MIN_VALUE;
        for (Field field: board.getEmptyFields()){
            field.setStatus(Field.FieldStatus.RED);
            int nodeValue = computeScoreAlgorithm.computeScore(field);
            int value = minValue(maxDepth-1,nodeValue,0);
            if (value > maxValue){
                maxValue = value;
                bestField = field;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return bestField;
    }

    private int maxValue(int maxDepth, int playerScore, int enemyScore){
        if (maxDepth==0 || board.getEmptyFields().isEmpty()){
            return playerScore - enemyScore;
        }
        int maxValue = Integer.MIN_VALUE;
        for (Field field: board.getEmptyFields()){
            field.setStatus(Field.FieldStatus.RED);
            int nodeValue = computeScoreAlgorithm.computeScore(field);
            int value = minValue(maxDepth-1,nodeValue + playerScore,enemyScore);
            if (value > maxValue){
                maxValue = value;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return maxValue;
    }
    private int minValue(int maxDepth, int playerScore, int enemyScore){
        if (maxDepth==0 || board.getEmptyFields().isEmpty()){
            return playerScore - enemyScore;
        }
        int minValue = Integer.MAX_VALUE;
        for (Field field : board.getEmptyFields()){
            field.setStatus(Field.FieldStatus.RED);
            int nodeValue = computeScoreAlgorithm.computeScore(field);
            int value = maxValue(maxDepth-1,playerScore,enemyScore + nodeValue);
            if (value < minValue){
                minValue = value;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return minValue;
    }


}
