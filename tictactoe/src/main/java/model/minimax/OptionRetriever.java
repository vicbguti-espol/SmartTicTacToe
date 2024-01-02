package model.minimax;

import java.util.LinkedList;
import model.board.Box;
import model.board.Board;
import model.game.TicTacToe;
import model.player.Player;
import tree.Tree;

public class OptionRetriever {
    private Board board;
    private Player player;
    private Player oponent;
    private Tree<Board> tree;
    
     public OptionRetriever(TicTacToe game){
        board = game.getBoard();
        player = game.getPlayer();
        oponent = game.getOponent();
        tree = new Tree<>(board);
    }
    
    public void buildTree(){
        useBoard(board, player, tree);
        
        for (Tree dTree: tree.getChildren()){
            useBoard((Board) dTree.getContent(), oponent, dTree);
        }
    }
    
    private void useBoard(Board board, Player player, Tree tree){
        for (int i = 0; i < board.ROWS * board.COLUMNS; i++){
            Box box = board.boxes[i];
            if (box.isEmpty()){
                Board cBoard = new Board(board);
                cBoard.setSymbol(player.getSymbol(), i);
                tree.getChildren().add(new Tree(cBoard));
            }
        }
    }

    public Tree<Board> getTree() {
        return tree;
    }
}
