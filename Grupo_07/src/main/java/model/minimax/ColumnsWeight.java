package model.minimax;

import model.board.Board;
import model.player.Player;

public class ColumnsWeight extends WeightCalculator{
    
    public ColumnsWeight (Board board, Player player) {
        super(board, player);
        this.traversalIterator = board.columnIterator();
    }
    
}
