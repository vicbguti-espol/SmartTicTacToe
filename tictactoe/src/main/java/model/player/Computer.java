package model.player;

import model.board.Symbol;

public class Computer extends Player {
    
    public Computer() {}
    
    public Computer(Symbol symbol) {
        super(symbol);
    }
    
    @Override
    public String toString() {
        return "Computadora";
    }
}
