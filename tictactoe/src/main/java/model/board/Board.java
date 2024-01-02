package model.board;

import controller.Subscriber;
import java.util.Iterator;
import java.util.List;
import iterator.*;
import java.util.LinkedList;


public class Board {
    public Integer ROWS = 3;
    public Integer COLUMNS = 3;
    public Box[] boxes;
    private List<Subscriber> suscribers;
    private Symbol winner;
    private int lastMovement;

    public int getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(int lastMovement) {
        this.lastMovement = lastMovement;
    }
    
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
        setLastMovement(arrayIndex);
        
        if (this.isWinner(symbol)){
            this.winner = symbol;
        }
        
        if (this.isWinner(symbol) || this.isFull()){
            this.notifySuscribers();
        }
    }
    
    public void setPosibleSymbol(Symbol symbol, int arrayIndex){
        boxes[arrayIndex].setSymbol(symbol);
        setLastMovement(arrayIndex);
    }
    
    public Symbol getWinner(){
        return winner;
    }
    
    public boolean isWinner(Symbol symbol){
        Iterator<Box[]>[] iterators = new Iterator[]{this.rowsIterator(), this.columnsIterator(), this.diagonalsIterator()};
        
        for (Iterator<Box[]> it: iterators){
            while (it.hasNext()){
                Box[] subBoxes = it.next();
                if (isWinner(symbol, subBoxes)){
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
        for (Subscriber s: suscribers){
            s.update();
        }
    }
    
    public void addSubscriber(Subscriber sub) {
        this.suscribers.add(sub);
    }

    
    private boolean isFull(){
        int i = 0;
        Box box = boxes[i];
        while(!box.isEmpty()){
            box = boxes[i++];
        }
        
        return i == ROWS*COLUMNS;
    }

    public Box[] getBoxes() {
        return boxes;
    }

    public Iterator<Box[]> columnsIterator() {
        return new IteratorByColumn(this);
    }

    public Iterator<Box[]> rowsIterator() {
        return new IteratorByRow(this);
    }

    public Iterator<Box[]> diagonalsIterator() {
        return new IteratorByDiagonal(this);
    }

    @Override
    public String toString() {
        String board = "Board{" ;
        for (Box box : boxes) {
            board += box.getSymbol() + ";";
        }
        return board + '}';
    }
}
    
