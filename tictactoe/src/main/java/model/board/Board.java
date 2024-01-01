package model.board;

import controller.Suscriber;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.board.iterators.*;


public class Board {
    public Integer ROWS = 3;
    public Integer COLUMNS = 3;
    public Box[] boxes;
    public List<Suscriber> suscribers;
    private Symbol winner;
    private int lastMovement;
    
    public Board(){
        suscribers = new LinkedList<>();
        boxes = new Box[ROWS*COLUMNS];
        for (int i = 0; i < ROWS*COLUMNS; i++){
            boxes[i] = new Box();
        }
    }
   
    public Board(Board board){
        this();
        for (int i = 0; i < ROWS*COLUMNS; i++){
            boxes[i] = new Box(board.boxes[i]);
        }
    }

    public void setSymbol(Symbol symbol, int arrayIndex){
        boxes[arrayIndex].setSymbol(symbol);
        this.setLastMovement(arrayIndex);
        
        if (this.isWinner(symbol)){
            this.winner = symbol;
        }
        
        if (this.isWinner(symbol) || this.isFull()){
            this.notifySuscribers();
        }
    }
    
    public Iterator<Box[]> rowIterator(){
        return new IteratorByRow(this);
    }
    
    public Iterator<Box[]> columnIterator(){
        return new IteratorByColumn(this);
    }
    
    public Iterator<Box[]> diagonalIterator(){
        return new IteratorByDiagonal(this);
    }
    
    public int getLastMovement() {
        return lastMovement;
    }

    private void setLastMovement(int lastMovement) {
        this.lastMovement = lastMovement;
    }
    
    public Symbol getWinner(){
        return winner;
    }
    
    public boolean isWinner(Symbol symbol){
        Iterator<Box[]>[] iterators = new Iterator[]{this.rowIterator(), this.columnIterator(), this.diagonalIterator()};
        
        for (Iterator<Box[]> it: iterators){
            while (it.hasNext()){
                Box[] subBoxes = it.next();
                if (this.isWinner(symbol, subBoxes)){
                    return isWinner(symbol, subBoxes);
                }
            }
        }
        return false;
    }
    
   
    private boolean isWinner(Symbol symbol, Box[] subBoxes){
        int emptyBoxes = 0;
        for (Box box: subBoxes){
            if (box.isEmpty()){
                emptyBoxes++;
            } else if (!box.getSymbol().equals(symbol)){
                return false;
            }  
        }
        return emptyBoxes != subBoxes.length;
    }
    
    private void notifySuscribers() {
        for (Suscriber s: suscribers){
            s.update();
        }
    }
    
    private boolean isFull(){
        int i = 0;
        Box box = boxes[i];
        while(!box.isEmpty()){
            box = boxes[i++];
        }
        
        return i == ROWS*COLUMNS;
    }

    @Override
    public String toString() {
        return "Board{" + "boxes=" + Arrays.toString(boxes) + '}';
    }
    
    
}
    
