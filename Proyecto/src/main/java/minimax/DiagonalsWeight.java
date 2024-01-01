package minimax;

import player.Player;

public class DiagonalsWeight extends WeightCalculator{
    
    public DiagonalsWeight (Board board, Player player) {
        super(board, player);
        this.traversalIterator = board.diagonalsIterator();
    }
    
}
