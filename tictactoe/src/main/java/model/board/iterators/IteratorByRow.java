package model.board.iterators;

import java.util.Arrays;
import java.util.Iterator;
import model.board.Board;
import model.board.Box;

public class IteratorByRow implements Iterator<Box[]>{
    Box[] boxes;
    int index;
    int max;
    
    public IteratorByRow(Board board){
        this.boxes = board.boxes;
        index = 0;
        max = 9;
    }

    @Override
    public boolean hasNext() {
        return index < max;
    }

    @Override
    public Box[] next() {
        Box[] row = Arrays.copyOfRange(boxes, index, index + 3);
        index += 3;
        return row;
    }
}
