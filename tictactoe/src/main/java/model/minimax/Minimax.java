package model.minimax;

import model.player.Player;
import java.util.Iterator;
import model.board.Board;
import model.game.TicTacToe;
import dstructure.Tree;
import model.game.Impossible;

public class Minimax {
    private TicTacToe game;
    private Board board;
    private Player player;
    private Player oponent;
    public Tree<Board> options;
    
    public Minimax(TicTacToe game) {
        this.game = game;
        this.board = game.getBoard();
        this.player = game.player;
        this.oponent = game.oponent;
    }
    
    public int calculate() {
       
        OptionRetriever optionRetriever = new OptionRetriever(game);
        optionRetriever.buildTree();
        this.options = optionRetriever.getTree();
        
        Board bestBoard = null;
        int maxUtility = -9;
        
        Iterator<Tree<Board>> children = options.getChildrenIterator();
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            
            for (Tree<Board> dTree: child.getChildren()){
                // useBoard(player, dTree);
                for (Tree<Board> sTree: dTree.getChildren()){
                    // useBoard(oponent, sTree);
                    seekMinimunUtility(sTree);
//                    for (Tree<Board> yTree: sTree.getChildren()){
//                        // useBoard(player, yTree);
//                    }
                }
            }
            
            
            int minUtility = seekMinimunUtility(child);
            child.getContent().setUtility(minUtility);
            final boolean isImpossible = game instanceof Impossible && !haveEnded(child);
            if (isImpossible || !(game instanceof Impossible)){
                if (minUtility > maxUtility) {
                    maxUtility = minUtility;
                    bestBoard = child.getRoot();
                }
            }
        }
        return bestBoard.getLastMovement();
    }
    
    private int seekMinimunUtility(Tree<Board> child) {
        int minUtility = 9;
        Iterator <Tree<Board>> grandChildren = child.getChildrenIterator();
        Board choosen = null;
        while (grandChildren.hasNext()) {
            Tree<Board> grandChild = grandChildren.next();
            Board board = grandChild.getRoot();
            int tmpUtility = calculateUtility(grandChild.getRoot());
            board.setUtility(tmpUtility);
            if (tmpUtility < minUtility) {
                minUtility = tmpUtility;
                choosen = board;
            }
        }
        return minUtility;
    }

    
    private int calculateUtility(Board board) {
        return calculatePlayerWeight(board, this.player) 
                - calculatePlayerWeight(board, this.oponent);
    }
    
    private int calculatePlayerWeight(Board board, Player player) {
        RowsWeight rowsWeight = new RowsWeight(board, player);
        ColumnsWeight columnsWeight = new ColumnsWeight(board, player);
        DiagonalsWeight diagonalsWeight = new DiagonalsWeight(board, player);
        int rw = rowsWeight.calculate();
        int cw = columnsWeight.calculate();
        int dw = diagonalsWeight.calculate();
        return rw + cw + dw;
    }

    private boolean haveEnded(Tree<Board> child) {
        for (Tree<Board> tree: child.getChildren()){ 
            if (tree.getContent().hasEnded) return true;
        }
        return false;
    }
    
    
}