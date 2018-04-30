import app.Board;
import app.Field;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.Collectors;

public class BoardTest {

    @Test
    public void createBoard() {
        Board board = new Board(5);
        Assert.assertTrue(board.getFields().size()==board.getSize()*board.getSize());
    }

    @Test
    public void getField() {
        Board board = new Board(5);
        Assert.assertTrue(board.getField(2,2).equals(board.getFields().stream().filter(field -> field.getCol()==2 && field.getRow()==2).collect(Collectors.toList()).get(0)));
    }


    @Test
    public void testIsBoardNotFilled() {
        Board board = new Board(5);
        Assert.assertTrue(!board.isFilled());
    }

    @Test
    public void testIsBoardFilled() {
        Board board = new Board(2);
        board.getField(0,0).setStatus(Field.FieldStatus.RED);
        board.getField(1,0).setStatus(Field.FieldStatus.RED);
        board.getField(0,1).setStatus(Field.FieldStatus.RED);
        board.getField(1,1).setStatus(Field.FieldStatus.RED);
        Assert.assertTrue(board.isFilled());

    }
}
