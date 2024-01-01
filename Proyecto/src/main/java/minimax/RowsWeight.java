package minimax;

import player.Player;

public class RowsWeight extends WeightCalculator{
    
    public RowsWeight (Board board, Player player) {
        super(board, player);
        this.traversalIterator = board.rowsIterator();
    }
    
}
