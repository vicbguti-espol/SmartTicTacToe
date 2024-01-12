package model.player;

import model.board.Symbol;

public abstract class Player {
    protected Symbol symbol;
    public int wins;
    
    public Player(){}

    public Player(Symbol symbol) {
        this.symbol = symbol;
        this.wins = 0;
    }

    public Symbol getSymbol() {
        return symbol;
    }
    
    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public String getColor() {
        return symbol.getColor();
    }

    public void setColor(String color) {
        symbol.setColor(color);
    }

    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("(").append(symbol).append(")");
        return sb.toString();
    }
    
    
}
