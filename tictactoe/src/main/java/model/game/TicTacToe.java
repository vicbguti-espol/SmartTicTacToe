package model.game;

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
        if (winnerSymbol != null){
            Queue<Player> cPlayers = new LinkedList<>(players);
            Player player = cPlayers.poll();
            return player.getSymbol().equals(winnerSymbol) ? player : cPlayers.poll();
        } else {return null;}
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return this.players.peek();
    }
    
    public Player getNext(){
        Player player = this.players.poll();
        this.players.offer(player);
        return player;
    }

    public Player getOponent() {
        Player firstPlayer = this.players.poll();
        Player oponent = this.players.poll();
        this.players.offer(firstPlayer);
        this.players.offer(oponent);
        return oponent;
    }
    
}
