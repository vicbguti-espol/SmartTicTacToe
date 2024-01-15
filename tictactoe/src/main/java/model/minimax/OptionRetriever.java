package model.minimax;

import model.board.Box;
import model.board.Board;
import model.game.TicTacToe;
import model.player.Player;
import dstructure.Tree;
import java.util.LinkedList;
import java.util.Queue;

public class OptionRetriever {
    private Board board;
    private Player player;
    private Player oponent;
    public Tree<Board> tree;
    
    public OptionRetriever(TicTacToe game){
        board = game.getBoard();
        player = game.getPlayer();
        oponent = game.getOponent();
        tree = new Tree<>(new Board(board));
    }
    
    public void buildTree(){
        buildTree(tree);
    }
    
    public void buildTree(Tree<Board> tree1){
        useBoard(player, tree1);
        
        for (Tree<Board> dTree: tree.getChildren()){
            useBoard(oponent, dTree);
            for (Tree<Board> sTree: dTree.getChildren()){
                useBoard(player, sTree);
                for (Tree<Board> yTree: sTree.getChildren()){
                    useBoard(oponent, yTree);
                    for (Tree<Board> xTree: yTree.getChildren()){
                        useBoard(player, xTree);
                        for (Tree<Board> pTree: xTree.getChildren()){
                            useBoard(oponent, pTree);
                        }
                    }
                }
            }
        }
    }
    
    private void useBoard(Player player, Tree tree){
        Board board = (Board) tree.getRoot();
        if (board.hasEnded) return;
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