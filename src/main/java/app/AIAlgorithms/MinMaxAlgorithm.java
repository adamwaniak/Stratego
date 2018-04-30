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
        return minmax(board, maxDepth);
    }

    private Field minmax(Board state, int maxDepth) {
        int bestScore = Integer.MIN_VALUE;
        Field bestField = null;
        for (Field field: state.getEmptyFields()){
            field.setStatus(Field.FieldStatus.RED);
            int playerMoveValue = computeScoreAlgorithm.computeScore(field);
            int enemyMoveValue = minValue(state,maxDepth-1);
            int score = playerMoveValue - enemyMoveValue;
            if (score>bestScore){
                bestScore = score;
                bestField = field;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return bestField;
    }

    private int maxValue(Board state, int maxDepth){
        if (maxDepth==0 || state.getEmptyFields().isEmpty()){
            return 0;
        }
        int bestScore = -1;
        for (Field field: state.getEmptyFields()){
            field.setStatus(Field.FieldStatus.RED);
            int playerMoveValue = computeScoreAlgorithm.computeScore(field);
            int enemyMoveValue = minValue(state,maxDepth-1);
            int score = playerMoveValue - enemyMoveValue;
            if (score>bestScore){
                bestScore = score;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return bestScore;
    }
    private int minValue(Board state, int maxDepth){
        if (maxDepth==0 || state.getEmptyFields().isEmpty()){
            return 0;
        }
        int bestScore = -1;
        for (Field field: state.getEmptyFields()){
            field.setStatus(Field.FieldStatus.RED);
            int enemyMoveValue = computeScoreAlgorithm.computeScore(field);
            int playerMoveValue = maxValue(state,maxDepth-1);
            int score = enemyMoveValue - playerMoveValue;
            if (score>bestScore){
                bestScore = score;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return bestScore;
    }


}
