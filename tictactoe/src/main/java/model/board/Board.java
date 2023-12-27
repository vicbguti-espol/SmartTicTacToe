package model.board;

import controller.Suscriber;
import java.util.Iterator;
import java.util.List;


public class Board {
    public Integer ROWS = 3;
    public Integer COLUMNS = 3;
    public Box[] boxes;
    private List<Suscriber> suscribers;
    private Symbol winner;
    
    public Board(){
        boxes = new Box[ROWS*COLUMNS];
    }
    
    public Board(Board board){
        this();
        for (int i = 0; i < ROWS*COLUMNS; i++){
            boxes[i] = new Box(board.boxes[i]);
        }
    }
        
//    public void setSymbol(Symbol symbol, int arrayIndex){
//        boxes[arrayIndex].setSymbol(symbol);
//        if (this.isFull()){
//            this.notifyTie();
//        }
//        
//        if (isWinner(symbol)){
//            this.winner = symbol;
//            this.notifyWinner();
//        }
//    }
    
    public Symbol getWinner(){
        return winner;
    }
    
//    public boolean isWinner(Symbol symbol){
//        Iterator<Box[]>[] iterators = new Iterator[]{this.rowIterator(), this.columnIterator(), this.diagonalIterator()};
//        
//        for (Iterator<Box[]> it: iterators){
//            while (it.hasNext()){
//                Box[] subBoxes = it.next();
//                if (isWinner(symbol, subBoxes)){
//                    return isWinner(symbol, subBoxes);
//                }
//            }
//        }
//        return false;
//    }
    
   
    private boolean isWinner(Symbol symbol, Box[] subBoxes){
        int i = 0;
        int length = subBoxes.length;
        Box box =  length > 0 ? subBoxes[i]: null;
        while (i++ < length && box.getSymbol().equals(symbol)){
            box = subBoxes[i];
        }
        return i == length;
    }
    
    private void notifyTie(){
        for (Suscriber s: suscribers){
            s.updateTie();
        }
    }
    
    private void notifyWinner(){
        for (Suscriber s: suscribers){
            s.updateWinner();
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
}
    
