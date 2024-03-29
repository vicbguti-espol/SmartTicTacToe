package model.minimax;

import model.board.Board;
import model.player.Player;

public class DiagonalsWeight extends WeightCalculator{
    
    public DiagonalsWeight (Board board, Player player) {
        super(board, player);
        this.traversalIterator = board.diagonalIterator();
    }
    
}
