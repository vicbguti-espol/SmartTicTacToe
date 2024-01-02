package test;

import java.util.Arrays;
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
        board.setSymbol(new Symbol('O'), 1);
        board.setSymbol(new Symbol('X'), 2);
        System.out.println(Arrays.toString(board.boxes));
    }
    
    void getLastMovement(){
        System.out.println(board.getLastMovement());
    }
}
