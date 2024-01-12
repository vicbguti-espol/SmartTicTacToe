package model.game;

import model.player.Bot;
import model.player.Human;

public class HumanComputer extends TicTacToe {
    
    public HumanComputer(){
        players.offer(new Human());
        players.offer(new Bot());
    }

    @Override
    public String toString() {
        return "Humano VS Computadora";
    }
    
}
