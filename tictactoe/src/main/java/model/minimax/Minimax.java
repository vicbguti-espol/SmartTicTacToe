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
        //this.player = game.player;
        //this.oponent = game.oponent;
        this.player = game.getPlayer();
        this.oponent = game.getOponent();
        System.out.println(player + " " + oponent);
    }
    
    public int calculate() {
       
        OptionRetriever optionRetriever = new OptionRetriever(game);
        optionRetriever.buildTree();
        this.options = optionRetriever.getTree();
        
        Board bestBoard = null;
        int maxUtility = -9;
        
        System.out.println("\n\nEMPIEZA MINIMAX");
        
        Iterator<Tree<Board>> children = options.getChildrenIterator();
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            System.out.println("\nEMPIEZA ITERACION");
            System.out.println("PRUEBA" +child.getRoot());
            
            
            int minUtility = seekFirstMinimunUtility(child);
            System.out.println("UTILIDAD MINIMA: " + minUtility);
            child.getContent().setUtility(minUtility);
            final boolean isImpossible = game instanceof Impossible && !haveEnded(child);
            if (isImpossible || !(game instanceof Impossible)){
                System.out.println("ENTRA?");
                if (minUtility > maxUtility) {
                    maxUtility = minUtility;
                    bestBoard = child.getRoot();
                    System.out.println("Mejor tablero: " + bestBoard);
                }
            }
        }
        return bestBoard.getLastMovement();
    }
    
    /*private int seekFirstMinimunUtility(Tree<Board> child) {
        int minUtility = 9;
        Iterator <Tree<Board>> grandChildren = child.getChildrenIterator();
        Board choosen = null;
        while (grandChildren.hasNext()) {
            Tree<Board> grandChild = grandChildren.next();
            Board board1 = grandChild.getRoot();
            int tmpUtility = calculateUtility(grandChild.getRoot());
            board1.setUtility(tmpUtility);
            if (tmpUtility < minUtility) {
                minUtility = tmpUtility;
                choosen = board1;
            }
        }
        return minUtility;
    }*/
    
    private int seekFirstMinimunUtility(Tree<Board> child) {
        int minUtility = 9;
        //System.out.println("UTILIDAD MINIMA");
        Iterator <Tree<Board>> grandChildren = child.getChildrenIterator();
        if (!grandChildren.hasNext()) {
            int tmpUtility = calculateUtility(child.getRoot());
            child.getContent().setUtility(tmpUtility);
            return tmpUtility;
        }
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
        if (!greatGrandChildren.hasNext()) {
            int tmpUtility = calculateUtility(grandChild.getRoot());
            grandChild.getContent().setUtility(tmpUtility);
            return tmpUtility;
        }
        while (greatGrandChildren.hasNext()) {
            Tree<Board> greatGrandChild = greatGrandChildren.next();
            int lastMinUtility = seekLastMinimunUtility(greatGrandChild);
            greatGrandChild.getContent().setUtility(lastMinUtility);
            if (lastMinUtility > maxUtility) {
                maxUtility = lastMinUtility;
            }
        }
        return maxUtility;
    }
    
    private int seekLastMinimunUtility(Tree<Board> greatGrandChild) {
        int minUtility = 9;
        Iterator <Tree<Board>> greatGreatGrandChildren = greatGrandChild.getChildrenIterator();
        if (!greatGreatGrandChildren.hasNext()) {
            int tmpUtility = calculateUtility(greatGrandChild.getRoot());
            greatGrandChild.getContent().setUtility(tmpUtility);
            return tmpUtility;
        }
        Board choosen = null;
        while (greatGreatGrandChildren.hasNext()) {
            Tree<Board> greatGreatGrandChild = greatGreatGrandChildren.next();
            int tmpUtility = calculateUtility(greatGreatGrandChild.getRoot());
            greatGreatGrandChild.getContent().setUtility(tmpUtility);
            if (tmpUtility < minUtility) {
                minUtility = tmpUtility;
            }
        }
        return minUtility;
    }
    
    

    
    private int calculateUtility(Board board) {
        if (board.getWinner() != null){
            if (board.getWinner().equals(player.getSymbol())) return 8;
            else if (board.getWinner().equals(oponent.getSymbol())) return -8;
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

    private boolean haveEnded(Tree<Board> child) {
        for (Tree<Board> tree: child.getChildren()){ 
            if (tree.getContent().hasEnded) return true;
        }
        return false;
    }
    
    
}