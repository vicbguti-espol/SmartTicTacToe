package model.game;

import model.player.Bot;


public class ComputerComputer extends TicTacToe {
    
    public ComputerComputer(){
        players.offer(new Bot());
        players.offer(new Bot());
    }

    @Override
    public String toString() {
        return "Computadora VS Computadora";
    }
    
    
}
