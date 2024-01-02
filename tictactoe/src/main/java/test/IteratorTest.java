package test;

import java.util.Arrays;
import java.util.Iterator;
import model.board.Board;
import model.board.Box;
import model.board.Symbol;

public class IteratorTest {
    
    public static void main(String[] args) {
        Board board = new Board();
        board.setPosibleSymbol(new Symbol('X'), 0);
        board.setPosibleSymbol(new Symbol('O'), 1);
        board.setPosibleSymbol(new Symbol('O'), 2);
        board.setPosibleSymbol(new Symbol('X'), 3);
        board.setPosibleSymbol(new Symbol('X'), 4);
        board.setPosibleSymbol(new Symbol('X'), 5);
        board.setPosibleSymbol(new Symbol('O'), 6);
        board.setPosibleSymbol(new Symbol('X'), 7);
        board.setPosibleSymbol(new Symbol('O'), 8);
        
        System.out.println("Recorridos de filas");
        Iterator<Box[]> itr = board.rowsIterator();
        while (itr.hasNext()) {
            System.out.println(Arrays.toString(itr.next()));
        }
        
        System.out.println("Recorridos de columnas");
        Iterator<Box[]> itc = board.columnsIterator();
        while (itc.hasNext()) {
            System.out.println(Arrays.toString(itc.next()));
        }
        
        System.out.println("Recorridos de diagonales");
        Iterator<Box[]> itd = board.diagonalsIterator();
        while (itd.hasNext()) {
            System.out.println(Arrays.toString(itd.next()));
        }
    }
    
}
