package model.player;

import model.board.Symbol;

public class Human extends Player {
    
    public Human(){}

    public Human(Symbol symbol) {
        super(symbol);
    }
        
    @Override
    public int playTurn() {
        return 0;
    }

    @Override
    public String toString() {
        return "Humano";
    }
    
}
