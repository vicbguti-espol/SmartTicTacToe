package model.minimax;

import model.board.Box;
import model.board.Board;
import model.game.TicTacToe;
import model.player.Player;
import dstructure.Tree;

public class OptionRetriever {
    private Board board;
    private Player player;
    private Player oponent;
    public Tree<Board> tree;
    
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
        
        for (Tree dTree: tree.getChildren()){
            for (Object tree3: dTree.getChildren()){
                Tree<Board> t = (Tree<Board>) tree3;
                useBoard(t.getContent(), player, t);
            }
            
        }
    }
    
    private void useBoard(Board board, Player player, Tree tree){
        if (board.getWinner() == null){ 
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

    public Tree<Board> getTree() {
        return tree;
    }
}