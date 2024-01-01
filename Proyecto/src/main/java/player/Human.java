package player;

import minimax.Symbol;

public class Human extends Player {

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
