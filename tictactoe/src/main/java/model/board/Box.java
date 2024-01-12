package model.board;

public class Box {
    public Symbol symbol;
    public int posX;
    public int posY;
   
    public Box(){
    }
    
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
        return symbol != null ? symbol + "": " ";
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }  
    
}
