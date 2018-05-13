package app.AIAlgorithms;

import app.Board;
import app.Field;

public class AlgorithmsFacade {

    private CloseLineAlgorithm closeLineAlgorithm;
    private RandomAlgorithm randomAlgorithm;
    private MinMaxAlgorithm minMaxAlgorithm;
    private AlphaBetaAlgorithm alphaBetaAlgorithm;
    private AlphaBetaAlgorithm alphaBetaAlgorithmPriorEnemy;
    private AlphaBetaAlgorithm alphaBetaAlgorithmPriorPlayer;

    public AlgorithmsFacade(Board board) {
        closeLineAlgorithm = new CloseLineAlgorithm(board);
        randomAlgorithm = new RandomAlgorithm(board);
        minMaxAlgorithm = new MinMaxAlgorithm(board, closeLineAlgorithm);
        alphaBetaAlgorithm = new AlphaBetaAlgorithm(board, closeLineAlgorithm, "");
        alphaBetaAlgorithmPriorEnemy = new AlphaBetaAlgorithm(board,closeLineAlgorithm,"enemy");
        alphaBetaAlgorithmPriorPlayer = new AlphaBetaAlgorithm(board,closeLineAlgorithm,"player");
    }

    public Field randomMove() {
        return randomAlgorithm.makeRandomMove();
    }

    public Field closeLineOrRandomMove() {
        return closeLineAlgorithm.closeLineOrRandom();
    }

    public Field minMaxAlgorithm(int maxDepth) {
        Field field = minMaxAlgorithm.getComputeField(maxDepth);
        if (field == null) {
            System.out.println("field is null from minmax");
        }
        return field;
    }

    public Field minMaxAlgorithm() {
        return minMaxAlgorithm.getComputeField();
    }

    public Field alphaBetaAlgorithm(int maxDepth) {
        Field field = alphaBetaAlgorithm.getComputeField(maxDepth);
        if (field == null) {
            System.out.println("field is null from alphabeta");
        }
        return field;
    }

    public Field alphaBetaAlgorithm(String prior, boolean order) {
        Field field;
        if (prior.equals("enemy")){
            field = alphaBetaAlgorithmPriorEnemy.getComputeField(order);
        }else if(prior.equals("player")){
            field = alphaBetaAlgorithmPriorPlayer.getComputeField(order);
        }else{
            field = alphaBetaAlgorithm.getComputeField(order);
        }
        if (field == null) {
            System.out.println("field is null from alphabeta");
        }
        return field;
    }


}
