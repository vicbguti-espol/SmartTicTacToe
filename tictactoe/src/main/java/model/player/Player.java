package model.player;

import model.board.Symbol;

public abstract class Player {
    protected Symbol symbol;
    
    public Player(){}

    public Player(Symbol symbol) {
        this.symbol = symbol;
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }
}
