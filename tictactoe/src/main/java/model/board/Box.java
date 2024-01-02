package model.board;

public class Box {
    private Symbol symbol;
   
    public Box(){}
    
    public Box(Symbol symbol){
        this.symbol = symbol;
    }
    
    public Box(Box box){
        this.symbol = box.symbol;
    }
    
    public boolean isEmpty(){
        return symbol == null;
    }
    
    public void setSymbol(Symbol symbol){
        this.symbol = symbol;
    }
    
    public Symbol getSymbol(){
        return symbol;
    }

    @Override
    public String toString() {
        return symbol + "";
    }
    
    
}
