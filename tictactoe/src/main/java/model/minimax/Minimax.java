package model.minimax;

import model.player.Player;
import java.util.Iterator;
import model.board.Board;
import model.game.TicTacToe;
//import tree.MiniMaxTree;
import dstructure.Tree;

public class Minimax {
    private TicTacToe game;
    private Board board;
    private Player player;
    private Player oponent;
    private Tree<Board> options;
    //private MiniMaxTree miniMaxTree;
    
    public Minimax(TicTacToe game) {
        this.game = game;
        this.board = game.getBoard();
        this.player = game.getPlayer();
        this.oponent = game.getOponent();
        //this.miniMaxTree = new MiniMaxTree();
    }
    
    public int calculate() {
        Board bestBoard = null;
        OptionRetriever optionRetriever = new OptionRetriever(game);
        optionRetriever.buildTree();
        this.options = optionRetriever.getTree();
        
        System.out.println(options.getContent());
        
        //this.miniMaxTree.setRoot(options.getRoot());
        
        int maxUtility = -9;
        
        Iterator<Tree<Board>> children = options.getChildrenIterator();
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            //System.out.println(child.getContent());
            
            //this.miniMaxTree.addChildren(child.getRoot());
            
            int minUtility = seekMinimunUtility(child);
            //System.out.println("Utilidad Minima:" + minUtility);
            //System.out.println("Utilidad Maxima:" + maxUtility);
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
                Tree<Board> grandChild = grandChildren.next();
                //System.out.println(grandChild.getContent());
                int tmpUtility = calculateUtility(grandChild.getRoot());
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
        int rw = rowsWeight.calculate();
        int cw = columnsWeight.calculate();
        int dw = diagonalsWeight.calculate();
        return rw + cw + dw;
        //return rowsWeight.calculate() + columnsWeight.calculate() + diagonalsWeight.calculate();
    }
}
