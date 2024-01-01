package model.minmax;

import dstructure.Tree;
import java.util.LinkedList;
import java.util.Queue;
import model.board.Box;
import model.board.Board;
import model.game.TicTacToe;
import model.player.Player;

public class OptionRetriever {
    private Board board;
    private Player player;
    private Player oponent;
    public Tree<Board> tree;
    
    public OptionRetriever(TicTacToe game){
        board = game.board;
        Queue<Player> players = new LinkedList<>(game.players);
        player = players.poll();
        oponent = players.poll();
        tree = new Tree<>(board);
    }
    
    public void buildTree(){
        useBoard(board, player, tree);
        
        for (Tree<Board> dTree: tree.getChildren()){
            useBoard(dTree.getContent(), oponent, dTree);
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
}
