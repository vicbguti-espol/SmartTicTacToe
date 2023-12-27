package model.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import model.board.Board;
import model.board.Symbol;

public class TicTacToe {
    private Queue<Player> players;
    private Board board;
    
    public TicTacToe(){
        players = new LinkedList<>();
    }
    
    public Queue<Player> getPlayers(){
        return players;
    }
    
    public Player getWinner(){
        Symbol winnerSymbol = board.getWinner();
        if (winnerSymbol != null){
            Iterator<Player> cPlayers = players.iterator();
            Player cPlayer = cPlayers.hasNext() ? cPlayers.next() : null;
            while (cPlayers.hasNext() && !cPlayer.getSymbol().equals(board.getWinner())){
                cPlayer = cPlayers.next();
            }
            return cPlayer;
        }
        return null;
    }
}
