package model.minimax;

import model.player.Player;
import java.util.Iterator;
import model.board.Board;
import model.game.TicTacToe;
import dstructure.Tree;
import java.util.LinkedList;
import java.util.Queue;
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
            
            
            int minUtility = seekMinUtility(child);
            System.out.println("UTILIDAD MINIMA: " + minUtility);
            child.getContent().setUtility(minUtility);
            final boolean isImpossible = game instanceof Impossible && !haveEnded(child);
            //if (isImpossible || !(game instanceof Impossible)){
            if (end(child.getContent())) {
                if (minUtility > maxUtility) {
                    maxUtility = minUtility;
                    bestBoard = child.getRoot();
                    System.out.println("Mejor tablero: " + bestBoard);
                }
            }
        }
        return bestBoard.getLastMovement();
    }
    
    private boolean end(Board board){
        return board.getWinner() == null || board.getWinner().equals(player.getSymbol());
    }
    
    private boolean hasLost(Board board){
        return board.getWinner() != null && board.getWinner().equals(oponent.getSymbol());
    }
    
    //Recursivo
    private int seekMinUtility(Tree<Board> tree) {
        int minUtility = 9;
        Iterator <Tree<Board>> children = tree.getChildrenIterator();
        if (!children.hasNext()) {
            int tmpUtility = calculateUtility(tree.getRoot());
            tree.getContent().setUtility(tmpUtility);
            return tmpUtility;
        }
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            int maxUtility = seekMaxUtility(child);
            child.getContent().setUtility(maxUtility);
            //if (end(grandChild.getContent())) {
            if (maxUtility < minUtility) {
                minUtility = maxUtility;
            //}
            }
        }
        return minUtility;
    }
    
    private int seekMaxUtility(Tree<Board> tree) {
        int maxUtility = -9;
        Iterator <Tree<Board>> children = tree.getChildrenIterator();
        if (!children.hasNext()) {
            int tmpUtility = calculateUtility(tree.getRoot());
            tree.getContent().setUtility(tmpUtility);
            return tmpUtility;
        }
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            int lastMinUtility = seekMinUtility(child);
            child.getContent().setUtility(lastMinUtility);
            //if (end(greatGrandChild.getContent())) {
            if (lastMinUtility > maxUtility) {
                maxUtility = lastMinUtility;
            }
            //}
        }
        return maxUtility;
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
    
    //ver si existen chances de ganar en una rama (no funciona)
    private boolean hasChances(Tree<Board> tree) {
        Queue<Tree<Board>> queue = new LinkedList();
        Tree<Board> root = tree;
        queue.offer(tree);
        while (!queue.isEmpty()) {
            Tree<Board> tmp = queue.poll();
            for (Tree<Board> child: tmp.getChildren()) {
                queue.offer(child);
                Board childBoard = child.getContent();
                System.out.println("Verifica si perdio " + childBoard + "" + hasLost(childBoard));
                if (hasLost(childBoard)) return false;
            }
        }
        return true;
    }

    private boolean haveEnded(Tree<Board> child) {
        for (Tree<Board> tree: child.getChildren()){ 
            if (tree.getContent().hasEnded) return true;
        }
        return false;
    }
    
    
}