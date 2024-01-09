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
        this.player = game.player;
        this.oponent = game.oponent;
        System.out.println(player + "" + oponent);
    }
    
    public int calculate() {
        Board bestBoard = null;
        OptionRetriever optionRetriever = new OptionRetriever(game);
        optionRetriever.buildTree();
        this.options = optionRetriever.getTree();
        int maxUtility = -9;
        
        Iterator<Tree<Board>> children = options.getChildrenIterator();
        while (children.hasNext()) {
            Tree<Board> child = children.next();
            int minUtility = seekMinimunUtility(child);
            child.getContent().setUtility(minUtility);
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
                Board board = grandChild.getRoot();
                int tmpUtility = calculateUtility(grandChild.getRoot());
                board.setUtility(tmpUtility);
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
    }
}
