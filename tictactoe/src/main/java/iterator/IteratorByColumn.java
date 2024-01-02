package iterator;

import model.board.Box;
import java.util.Iterator;
import model.board.Board;

public class IteratorByColumn implements Iterator<Box[]>{
    Box[] boxes;
    int index;
    int max;
    
    public IteratorByColumn(Board board){
        this.boxes = board.getBoxes();
        this.index = 0;
        this.max = 3;
    }

    @Override
    public boolean hasNext() {
        return index < 3;
    }

    @Override
    public Box[] next() {
        Box[] column = new Box[3];
        for (int i = 0; i < 3; i++){
            column[i] = boxes[index + i*3];
        }
        index++;
        return column;
    }
}
