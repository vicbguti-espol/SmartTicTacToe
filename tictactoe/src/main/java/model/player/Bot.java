package model.player;

import model.board.Symbol;

public class Bot extends Player {
    
    public Bot(){}
    
    public Bot(Symbol symbol) {
        super(symbol);
    }
    
    @Override
    public String toString() {
        return "Computadora";
    }
    
}