package minimax;

import model.player.Player;
import java.util.Iterator;
import tree.MiniMaxTree;
import dstructure.Tree;

public class Minimax {
    private Board board;
    private Player player;
    private Player oponent;
    private Tree<Board> options;
    //private MiniMaxTree miniMaxTree;
    
    public Minimax(TicTacToe game) {
        this.board = game.getBoard();
        this.player = game.getPlayer();
        this.oponent = game.getOponent();
        //this.miniMaxTree = new MiniMaxTree();
    }
    
    public int calculate() {
        Board bestBoard = null;
        
        //this.miniMaxTree.setRoot(options.getRoot());
        
        int maxUtility = -9;
        
        Iterator<Tree<Board>> children = options.getChildrenIterator();
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            
            //this.miniMaxTree.addChildren(child.getRoot());
            
            int minUtility = seekMinimunUtility(child);
            if (minUtility > maxUtility) {
                bestBoard = child.getRoot();
                maxUtility = minUtility;
            }
        }
        return bestBoard.getLastMovement();
    }
    
    private int seekMinimunUtility(Tree<Board> child) {
        int minUtility = 9;
            Iterator <Tree<Board>> grandChildren = child.getChildrenIterator();
            while (grandChildren.hasNext()) {
                int tmpUtility = calculateUtility(grandChildren.next().getRoot());
                if (tmpUtility < minUtility) {
                    minUtility = tmpUtility;
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
        return rowsWeight.calculate() + columnsWeight.calculate() + diagonalsWeight.calculate();
    }
}
