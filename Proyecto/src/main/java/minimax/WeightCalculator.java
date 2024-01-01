package minimax;

import player.Player;
import java.util.Iterator;

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
            if (!line[i].getSymbol().equals(player.getSymbol())) {
                return false;
            }
        }
        return true;
    }
}
