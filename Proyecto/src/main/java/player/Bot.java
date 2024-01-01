package player;

import minimax.Symbol;
import minimax.TicTacToe;

public class Bot extends Player {
    
    public Bot(Symbol symbol) {
        super(symbol);
    }
    
    @Override
    public int playTurn() {
        return 0;
    }
    
    @Override
    public String toString() {
        return "Computadora";
    }
    
}
