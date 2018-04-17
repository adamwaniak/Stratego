import java.util.Objects;

public class Field {

    public Field() {
        this(-1, -1);

    }

    public Field(int col, int row) {
        this.col = col;
        this.row = row;
        status = FieldStatus.EMPTY;
    }

    private int col;
    private int row;
    private FieldStatus status;

    public enum FieldStatus {
        RED, BLUE, EMPTY
    }

    public int getCol() {
        return col;
    }

    public Field setCol(int col) {
        this.col = col;
        return this;
    }

    public int getRow() {
        return row;
    }

    public Field setRow(int row) {
        this.row = row;
        return this;
    }

    public FieldStatus getStatus() {
        return status;
    }

    public Field setStatus(FieldStatus status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Field field = (Field) o;
        return col == field.col &&
                row == field.row &&
                status == field.status;
    }

    @Override
    public int hashCode() {

        return Objects.hash(col, row, status);
    }

    @Override
    public String toString() {
        return "Field{" +
                "col=" + col +
                ", row=" + row +
                ", status=" + status +
                '}';
    }

    public String statusToString() {
        String fieldStatus;
        switch (status) {
            case RED:
                fieldStatus = "R";
                break;
            case BLUE:
                fieldStatus = "B";
                break;
            default:
                fieldStatus = " ";
        }
        return fieldStatus;
    }

    public Player.Color statusToColor() {
        switch (status) {
            case RED:
                return Player.Color.RED;
            case BLUE:
                return Player.Color.BLUE;
            default:
                System.out.println("Field.statusToColor, try get status to color with empty field");
                return null;
        }
    }
}
