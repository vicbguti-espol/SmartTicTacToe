package test;
import model.board.Board;
import model.board.Symbol;
import model.game.TicTacToe;
import model.player.Bot;
import model.player.Human;

public class BoardTest {
    public Board board;
    TicTacToe game;
    
    public BoardTest(){
        game = new TicTacToe();
        game.players.add(new Human(new Symbol('X')));
        game.players.add(new Bot(new Symbol('O')));
        board = new Board();
        game.board = board;
        board.suscribers.add(()->{
            System.out.println("ganador: " + game.getWinner());
        });

    }
    
    void setSymbol(){
        board.setSymbol(new Symbol('X'), 0);
        board.setSymbol(new Symbol('O'), 4);
        board.setSymbol(new Symbol('X'), 5);
        board.setSymbol(new Symbol('O'), 2);
        board.setSymbol(new Symbol('X'), 1);
        board.setSymbol(new Symbol('O'), 6);
        System.out.println(board);
    }
    
    public static void main(String[] args) {
        BoardTest bt = new BoardTest();
        bt.setSymbol();
    }
    
}
