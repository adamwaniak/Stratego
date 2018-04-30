package app.AIAlgorithms;

import app.Board;
import app.Field;

public class AlgorithmsFacade {

    private CloseLineAlgorithm closeLineAlgorithm;
    private RandomAlgorithm randomAlgorithm;

    public AlgorithmsFacade(Board board) {
        closeLineAlgorithm = new CloseLineAlgorithm(board);
        randomAlgorithm = new RandomAlgorithm(board);
    }

    public Field randomMove(){
        return randomAlgorithm.makeRandomMove();
    }

    public Field closeLineOrRandomMove(){
        return closeLineAlgorithm.closeLineOrRandom();
    }

}
