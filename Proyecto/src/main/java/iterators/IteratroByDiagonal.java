package iterators;

import minimax.Box;
import java.util.Iterator;
import minimax.Board;

public class IteratroByDiagonal implements Iterator<Box[]>{
    Box[] boxes;
    int index;
    int max;
    
    public IteratroByDiagonal(Board board){
        this.boxes = board.getBoxes();
        index = 0;
        max = 3;
    }

    @Override
    public boolean hasNext() {
        return index < max;
    }

    @Override
    public Box[] next() {
        Box[] diagonal = new Box[3];
        for (int i = 0; i < 3; i++) {
            if (index == 0) diagonal[i] = boxes[index + i*4];
            else if(index == 2) diagonal[i] = boxes[index + i*2];
        }
        index += 2;
        return diagonal;
    }
    
    
}
