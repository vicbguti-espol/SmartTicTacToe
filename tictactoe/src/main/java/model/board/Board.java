package model.board;

import controller.Subscriber;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import model.board.iterators.*;



public class Board {
    public Integer ROWS = 3;
    public Integer COLUMNS = 3;
    public Box[] boxes;
    public List<Subscriber> suscribers;
    private Symbol winner;
    private int lastMovement;
    private int utility;
    public boolean hasEnded;
    
    public Board(){
        hasEnded = false;
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
    
    public void map(BoxOperation operation){
        Iterator<Box[]> rowIterator = this.rowIterator();
        int i = 0;
        while (rowIterator.hasNext()){
            i++;
            Box[] row = rowIterator.next();
            for (int j = 0; j < row.length; j++){
                Box box = row[j];
                box.setPosX(i);
                box.setPosY(j);
                operation.execute(box);
            }
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
            this.hasEnded = true;
        }
    }
    
    public void setPosibleSymbol(Symbol symbol, int arrayIndex){
        boxes[arrayIndex].setSymbol(symbol);
        setLastMovement(arrayIndex);
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
        int count = 0;
        for (Box box: subBoxes){
            if (box.getSymbol() != null && box.getSymbol().equals(symbol)){
                count++;
            }
        }
        return count == subBoxes.length;
    }
    
    private void notifySuscribers() {
        for (Subscriber s: suscribers){
            s.update();
        }
    }
    
    public void addSubscriber(Subscriber sub) {
        this.suscribers.add(sub);
    }
    
    public boolean isFull(){
        int i = 0;
        Box box = boxes[i];
        while(!box.isEmpty() && i < ROWS*COLUMNS - 1){ 
            box = boxes[i++];
        }
        
        return i == ROWS*COLUMNS;
    }

    public Box[] getBoxes() {
        return boxes;
    }

    public int getUtility() {
        return utility;
    }

    public void setUtility(int utility) {
        this.utility = utility;
    }
    
    @Override
    public String toString() {
        String board = "Board{\n" ;
        Iterator<Box[]> it = this.rowIterator();
        while (it.hasNext()){
            Box[] row = it.next();
            board += Arrays.toString(row) + "\n";
        }
        return board + '}';
    }
}
    
