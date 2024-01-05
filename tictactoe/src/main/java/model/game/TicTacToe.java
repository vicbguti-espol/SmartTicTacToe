package model.game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import model.board.Board;
import model.board.Symbol;
import model.player.Player;

public class TicTacToe {
    public Queue<Player> players;
    public Board board;
    
    public TicTacToe(){
        players = new LinkedList<>();
        board = new Board();
    }
    
    public Queue<Player> getPlayers(){
        return players;
    }

    public void setPlayers(Queue<Player> players) {
        this.players = players;
    }
    
    
    public Player getWinner(){
        Symbol winnerSymbol = board.getWinner();
        System.out.println(winnerSymbol);
        if (winnerSymbol != null){
            Iterator<Player> cPlayers = players.iterator();
            Player cPlayer = cPlayers.hasNext() ? cPlayers.next() : null;
            while (cPlayers.hasNext() && cPlayer != null && !cPlayer.getSymbol().equals(board.getWinner())){
                cPlayer = cPlayers.next();
            }
            return cPlayer;
        }
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return this.players.peek();
    }

    public Player getOponent() {
        Player firstPlayer = this.players.poll();
        Player oponent = this.players.poll();
        this.players.offer(firstPlayer);
        this.players.offer(oponent);
        return oponent;
    }
    
}
