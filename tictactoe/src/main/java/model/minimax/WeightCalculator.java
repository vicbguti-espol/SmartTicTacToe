package model.minimax;

import java.util.Iterator;
import model.board.Board;
import model.board.Box;
import model.board.Symbol;
import model.player.Player;

public abstract class WeightCalculator {
    protected int weight;
    protected Iterator<Box[]> traversalIterator;
    protected Board board;
    protected Player player;

    public WeightCalculator(Board board, Player player) {
        this.board = board;
        this.player = player;
        this.weight = 0;
    }
    
    public int calculate() {
        while (traversalIterator.hasNext()) {
            if ( isAvailable(traversalIterator.next()) ) {
                weight++;
            }
        }
        return weight;
    }
    
    private boolean isAvailable(Box[] line){
        for (int i = 0; i < line.length; i++) {
            Symbol boxSymbol = line[i].getSymbol();
            if (boxSymbol != null && !line[i].getSymbol().equals(player.getSymbol())) {
                return false;
            }
        }
        return true;
    }
}
