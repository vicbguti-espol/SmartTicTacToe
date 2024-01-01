package player;

import minimax.Symbol;

public abstract class Player {
    protected Symbol symbol;

    public Player(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public abstract int playTurn();
}
