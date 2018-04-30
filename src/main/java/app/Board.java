package app;

import java.util.*;
import java.util.stream.Collectors;

public class Board {

    private int size;
    private List<Field> fields;

    public Board(int size) {
        this.size = size;
        this.fields = getFilledBoard(size);
    }

    public Board(Board other){
        this.size = other.size;
        this.fields = new ArrayList<>();
        for (Field field: other.fields){
            this.fields.add(new Field(field));
        }
    }


    public Field getField(int row, int col) {
        return fields.stream().filter((Field field) -> field.getRow() == row && field.getCol() == col).collect(Collectors.toList()).get(0);
    }

    public int getSize() {
        return size;
    }

    public Board setSize(int size) {
        this.size = size;
        return this;
    }

    public List<Field> getFields() {
        return fields;
    }

    public Board setFields(List<Field> fields) {
        this.fields = fields;
        return this;
    }

    public List<Field> getEmptyFields(){
        return fields.stream().filter(it -> it.getStatus()==Field.FieldStatus.EMPTY).collect(Collectors.toList());
    }

    private List<Field> getFilledBoard(int size) {
        List<Field> fields = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fields.add(new Field(i, j));
            }
        }
        return fields;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return size == board.size &&
                Objects.equals(fields, board.fields);
    }

    @Override
    public int hashCode() {

        return Objects.hash(size, fields);
    }

    @Override
    public String toString() {
        return "app.Board{" +
                "size=" + size +
                ", fields=" + fields +
                '}';
    }

    public boolean isFilled() {
        return fields.stream().filter(field -> field.getStatus() == Field.FieldStatus.EMPTY).collect(Collectors.toList()).isEmpty();
    }

    public Set<Field> getColumn(int colnum) {
        return getFields().stream().filter(field -> field.getCol() == colnum).collect(Collectors.toSet());
    }

    public Set<Field> getRow(int rownum) {
        return getFields().stream().filter(field -> field.getRow() == rownum).collect(Collectors.toSet());
    }

    public Set<Field> getLeftDiagonal(Field field) {

        Set<Field> diagonal = new HashSet<>();
        int i = field.getRow();
        int j = field.getCol();
        int size = getSize();
        while (i >= 0 && j < size) {
            diagonal.add(getField(i, j));
            i--;
            j++;
        }
        i = field.getRow();
        j = field.getCol();
        while (i < size && j >= 0) {
            diagonal.add(getField(i, j));
            i++;
            j--;
        }
        return diagonal;
    }

    public Set<Field> getRightDiagonal(Field field) {

        Set<Field> diagonal = new HashSet<>();
        int i = field.getRow();
        int j = field.getCol();
        int size = getSize();
        while (i < size && j < size) {
            diagonal.add(getField(i, j));
            i++;
            j++;
        }

        i = field.getRow();
        j = field.getCol();
        while (i >= 0 && j >= 0) {
            diagonal.add(getField(i, j));
            i--;
            j--;
        }
        return diagonal;
    }
}
