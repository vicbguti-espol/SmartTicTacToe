package model.game;

import model.player.Human;


public class HumanHuman extends TicTacToe {
    public HumanHuman(){
        players.offer(new Human());
        players.offer(new Human());
    }

    @Override
    public String toString() {
        return "HumanHuman{" + '}';
    }
    
    
}
