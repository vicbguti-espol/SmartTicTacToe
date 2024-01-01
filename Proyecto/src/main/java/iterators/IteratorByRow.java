package iterators;

import minimax.Box;
import java.util.Arrays;
import java.util.Iterator;
import minimax.Board;

public class IteratorByRow implements Iterator<Box[]>{
    Box[] boxes;
    int index;
    int max;
    
    public IteratorByRow(Board board){
        this.boxes = board.getBoxes();
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
