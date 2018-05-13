package app.AIAlgorithms;

import app.Board;
import app.ComputeScoreAlgorithm;
import app.Field;

public class AlphaBetaAlgorithm {

    private Board board;
    private ComputeScoreAlgorithm computeScoreAlgorithm;
    private CloseLineAlgorithm closeLineAlgorithm;

    public AlphaBetaAlgorithm(Board board, CloseLineAlgorithm closeLineAlgorithm) {
        this.board = board;
        computeScoreAlgorithm = new ComputeScoreAlgorithm();
        computeScoreAlgorithm.setBoard(board);
        this.closeLineAlgorithm = closeLineAlgorithm;

    }

    public Field getComputeField() {
        int size = board.getEmptyFields().size();
        System.out.println("size: " + size);
        if (size > 70) return closeLineAlgorithm.closeLineOrRandom();

        if (size > 50) return alphaBeta(2);

        if (size > 30) return alphaBeta(3);

        if (size > 25) return alphaBeta(4);

        if (size > 10) return alphaBeta(5);

        if (size > 5) alphaBeta(6);
        return alphaBeta(7);
    }

    public Field getComputeField(int maxDepth) {
        return alphaBeta(maxDepth);
    }

    private Field alphaBeta(int maxDepth) {
        Field bestField = null;
        int maxValue = Integer.MIN_VALUE;
        int aRoot = Integer.MIN_VALUE;
        int bRoot = Integer.MAX_VALUE;
        for (Field field : board.getEmptyFields()) {
            field.setStatus(Field.FieldStatus.RED);
            int nodeValue = computeScoreAlgorithm.computeScore(field);
            int value = minValue(maxDepth - 1, nodeValue, 0, aRoot, bRoot);
            if (value > maxValue) {
                maxValue = value;
                bestField = field;
            }
            field.setStatus(Field.FieldStatus.EMPTY);
        }
        return bestField;
    }

    private int maxValue(int maxDepth, int playerScore, int enemyScore, int aRoot, int bRoot) {
        if (maxDepth == 0 || board.getEmptyFields().isEmpty()) {
            return playerScore - enemyScore;
        }
        int a = aRoot;
        int b = bRoot;
        int maxValue = Integer.MIN_VALUE;
        for (Field field : board.getEmptyFields()) {
            field.setStatus(Field.FieldStatus.RED);
            int nodeValue = computeScoreAlgorithm.computeScore(field);
            int value = minValue(maxDepth - 1, nodeValue + playerScore, enemyScore, a, b);
            if (value > maxValue) {
                maxValue = value;
                if (value > a) {
                    a = value;
                }
            }
            field.setStatus(Field.FieldStatus.EMPTY);

            if (value > b) {
                break;
            }
        }
        return maxValue;
    }

    private int minValue(int maxDepth, int playerScore, int enemyScore, int aRoot, int bRoot) {
        if (maxDepth == 0 || board.getEmptyFields().isEmpty()) {
            return playerScore - enemyScore;
        }
        int a = aRoot;
        int b = bRoot;
        int minValue = Integer.MAX_VALUE;
        for (Field field : board.getEmptyFields()) {
            field.setStatus(Field.FieldStatus.RED);
            int nodeValue = computeScoreAlgorithm.computeScore(field);
            int value = maxValue(maxDepth - 1, playerScore, enemyScore + nodeValue, a, b);
            if (value < minValue) {
                minValue = value;
                if (value < b) {
                    b = value;
                }
            }
            field.setStatus(Field.FieldStatus.EMPTY);

            if (value < a) {
                break;
            }
        }
        return minValue;
    }

}
