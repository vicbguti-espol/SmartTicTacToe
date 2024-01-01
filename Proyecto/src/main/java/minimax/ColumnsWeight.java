package minimax;

import player.Player;

public class ColumnsWeight extends WeightCalculator{
    
    public ColumnsWeight (Board board, Player player) {
        super(board, player);
        this.traversalIterator = board.columnsIterator();
    }
    
}
