package model.minimax;

import model.player.Player;
import java.util.Iterator;
import model.board.Board;
import model.game.TicTacToe;
import dstructure.Tree;

public class Minimax {
    private TicTacToe game;
    private Board board;
    private Player player;
    private Player oponent;
    public Tree<Board> options;
    
    public Minimax(TicTacToe game) {
        this.game = game;
        this.board = game.getBoard();
        //this.player = game.player;
        //this.oponent = game.oponent;
        this.player = game.getPlayer();
        this.oponent = game.getOponent();
        //System.out.println(player + "" + oponent);
    }
    
    public int calculate() {
        Board bestBoard = null;
        OptionRetriever optionRetriever = new OptionRetriever(game);
        optionRetriever.buildTree();
        this.options = optionRetriever.getTree();
        int maxUtility = -9;
        
        Iterator<Tree<Board>> children = options.getChildrenIterator();
        while (children.hasNext()) {
            System.out.println("ENTRO?");
            Tree<Board> child = children.next();
            int minUtility = seekMinimunUtility(child);
            System.out.println("UTILIDAD min:" + minUtility);
            child.getContent().setUtility(minUtility);
            if (minUtility > maxUtility) {
                bestBoard = child.getRoot();
                maxUtility = minUtility;
            }
        }
        System.out.println(bestBoard);
        return bestBoard.getLastMovement();
    }
    
    private int seekMinimunUtility(Tree<Board> child) {
        int minUtility = 9;
            Iterator <Tree<Board>> grandChildren = child.getChildrenIterator();
            while (grandChildren.hasNext()) {
                Tree<Board> grandChild = grandChildren.next();
                Board board = grandChild.getRoot();
                int tmpUtility = calculateUtility(grandChild.getRoot());
                board.setUtility(tmpUtility);
                if (tmpUtility < minUtility) {
                    minUtility = tmpUtility;
                }
            }
        return minUtility;
    }
    
    /*private int seekMinimunUtility(Tree<Board> child) {
        int minUtility = 9;
            Iterator <Tree<Board>> grandChildren = child.getChildrenIterator();
            while (grandChildren.hasNext()) {
                Tree<Board> grandChild = grandChildren.next();
                int maxUtility = seekMaximunUtility(grandChild);
                grandChild.getContent().setUtility(maxUtility);
                if (maxUtility < minUtility) {
                    minUtility = maxUtility;
                }
            }
        return minUtility;
    }
    
    private int seekMaximunUtility(Tree<Board> grandChild) {
        int maxUtility = -9;
            Iterator <Tree<Board>> greatGrandChildren = grandChild.getChildrenIterator();
            while (greatGrandChildren.hasNext()) {
                Tree<Board> greatGrandChild = greatGrandChildren.next();
                Board board = greatGrandChild.getRoot();
                int tmpUtility = calculateUtility(greatGrandChild.getRoot());
                board.setUtility(tmpUtility);
                if (tmpUtility > maxUtility) {
                    maxUtility = tmpUtility;
                }
            }
        return maxUtility;
    }*/
    
    private int calculateUtility(Board board) {
        if (board.getWinner() != null){
            //if (board.getWinner().equals(player.getSymbol())) return 8;
             if (board.getWinner().equals(oponent.getSymbol())) return -8;
        }
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
}
