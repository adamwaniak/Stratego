package app.AIAlgorithms;

import app.Board;
import app.Field;

public class AlgorithmsFacade {

    private CloseLineAlgorithm closeLineAlgorithm;
    private RandomAlgorithm randomAlgorithm;
    private MinMaxAlgorithm minMaxAlgorithm;
    private AlphaBetaAlgorithm alphaBetaAlgorithm;

    public AlgorithmsFacade(Board board) {
        closeLineAlgorithm = new CloseLineAlgorithm(board);
        randomAlgorithm = new RandomAlgorithm(board);
        minMaxAlgorithm = new MinMaxAlgorithm(board);
        alphaBetaAlgorithm = new AlphaBetaAlgorithm(board);
    }

    public Field randomMove(){
        return randomAlgorithm.makeRandomMove();
    }

    public Field closeLineOrRandomMove(){
        return closeLineAlgorithm.closeLineOrRandom();
    }

    public Field minMaxAlgorithm(int maxDepth){
        Field field =  minMaxAlgorithm.getComputeField(maxDepth);
        if (field==null){
            System.out.println("field is null from minmax");
        }
        return field;
    }

    public Field alphaBetaAlgorithm(int maxDepth){
        Field field =  alphaBetaAlgorithm.getComputeField(maxDepth);
        if (field==null){
            System.out.println("field is null from alphabeta");
        }
        return field;
    }

    public Field alphaBetaAlgorithm(){
        Field field =  alphaBetaAlgorithm.getComputeField();
        if (field==null){
            System.out.println("field is null from alphabeta");
        }
        return field;
    }


}
