package test;
import model.board.Board;
import model.board.Symbol;

public class BoardTest {
    Board board;
    
    BoardTest(){
        board = new Board();
        board.suscribers.add(()->{
            System.out.println("ganador: " + board.getWinner());
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
    
    void getLastMovement(){
        System.out.println(board.getLastMovement());
    }
    
    public static void main(String[] args) {
        BoardTest bt = new BoardTest();
        bt.setSymbol();
    }
    
}
