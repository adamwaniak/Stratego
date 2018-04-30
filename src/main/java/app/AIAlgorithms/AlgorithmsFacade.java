package app.AIAlgorithms;

import app.Board;
import app.Field;

public class AlgorithmsFacade {

    private CloseLineAlgorithm closeLineAlgorithm;
    private RandomAlgorithm randomAlgorithm;
    private MinMaxAlgorithm minMaxAlgorithm;
    public AlgorithmsFacade(Board board) {
        closeLineAlgorithm = new CloseLineAlgorithm(board);
        randomAlgorithm = new RandomAlgorithm(board);
        minMaxAlgorithm = new MinMaxAlgorithm(board);
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
}
