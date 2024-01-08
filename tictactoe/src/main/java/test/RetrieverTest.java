package test;

import dstructure.Tree;
import model.board.Board;
import model.board.Symbol;
import model.game.TicTacToe;
import model.minimax.OptionRetriever;
import model.player.Bot;
import model.player.Human;

public class RetrieverTest {
    OptionRetriever optionRetriever;
    TicTacToe game;
    
    RetrieverTest(){
        game = new TicTacToe();
        buildGame();
        optionRetriever = new OptionRetriever(game);
        game.board.boxes[1].setSymbol(new Symbol('X'));
        game.board.boxes[4].setSymbol(new Symbol('O'));
    }
    
    private void buildGame(){
        game.players.offer(new Human(new Symbol('X')));
        game.players.offer(new Bot(new Symbol('O')));
    }
    
    void printTree(){
        optionRetriever.buildTree();
        Tree<Board> tree = optionRetriever.tree;
        System.out.println(tree.getRoot());
    }
    
    public static void main(String[] args) {
        RetrieverTest rTest = new RetrieverTest();
        rTest.printTree();
    }
}
