import org.junit.Assert;
import org.junit.Test;

public class ComputeScoreAlgorithmTest {

    @Test
    public void rowIsFilled() {
        Board board = new Board(2);
        Player redPlayer = new Player(Player.Color.RED);
        Player bluePlayer = new Player(Player.Color.BLUE);
        ComputeScoreAlgorithm computeScoreAlgorithm = new ComputeScoreAlgorithm(board,redPlayer,bluePlayer);
        board.getField(0,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(0,1).setStatus(Field.FieldStatus.RED);
        Assert.assertTrue(computeScoreAlgorithm.isRowFilled(board.getField(0,0)));
    }

    @Test
    public void rowIsNotFilled() {
        Board board = new Board(2);
        Player redPlayer = new Player(Player.Color.RED);
        Player bluePlayer = new Player(Player.Color.BLUE);
        ComputeScoreAlgorithm computeScoreAlgorithm = new ComputeScoreAlgorithm(board,redPlayer,bluePlayer);
        board.getField(0,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(1,1).setStatus(Field.FieldStatus.RED);
        Assert.assertTrue(!computeScoreAlgorithm.isRowFilled(board.getField(0,0)));
    }

    @Test
    public void colIsFilled() {
        Board board = new Board(2);
        Player redPlayer = new Player(Player.Color.RED);
        Player bluePlayer = new Player(Player.Color.BLUE);
        ComputeScoreAlgorithm computeScoreAlgorithm = new ComputeScoreAlgorithm(board,redPlayer,bluePlayer);
        board.getField(0,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(1,0).setStatus(Field.FieldStatus.RED);
        Assert.assertTrue(computeScoreAlgorithm.isColumnFilled(board.getField(0,0)));
    }

    @Test
    public void rightDiagonalIsFilled() {
        Board board = new Board(4);
        Player redPlayer = new Player(Player.Color.RED);
        Player bluePlayer = new Player(Player.Color.BLUE);
        ComputeScoreAlgorithm computeScoreAlgorithm = new ComputeScoreAlgorithm(board,redPlayer,bluePlayer);
        board.getField(1,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(0,1).setStatus(Field.FieldStatus.RED);
        Assert.assertTrue(computeScoreAlgorithm.isLeftDiagonalFilled(board.getField(0,1)));
    }


    @Test
    public void computeScore1() {
        Board board = new Board(4);
        Player redPlayer = new Player(Player.Color.RED);
        Player bluePlayer = new Player(Player.Color.BLUE);
        ComputeScoreAlgorithm computeScoreAlgorithm = new ComputeScoreAlgorithm(board,redPlayer,bluePlayer);
        board.getField(0,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(0,1).setStatus(Field.FieldStatus.BLUE);
        board.getField(0,2).setStatus(Field.FieldStatus.RED);
        board.getField(0,3).setStatus(Field.FieldStatus.BLUE);
        computeScoreAlgorithm.computeAndAssignScoreIfPossible(board.getField(0,1));
        System.out.println(bluePlayer.getScore());
        Assert.assertTrue(bluePlayer.getScore()==2);
    }

    @Test
    public void computeScore2() {
        Board board = new Board(4);
        Player redPlayer = new Player(Player.Color.RED);
        Player bluePlayer = new Player(Player.Color.BLUE);
        ComputeScoreAlgorithm computeScoreAlgorithm = new ComputeScoreAlgorithm(board,redPlayer,bluePlayer);
        board.getField(0,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(1,0).setStatus(Field.FieldStatus.BLUE);
        board.getField(2,0).setStatus(Field.FieldStatus.RED);
        board.getField(3,0).setStatus(Field.FieldStatus.BLUE);
        computeScoreAlgorithm.computeAndAssignScoreIfPossible(board.getField(0,0));
        Assert.assertTrue(bluePlayer.getScore()==2);
    }
}
