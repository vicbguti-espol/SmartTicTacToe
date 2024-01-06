package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.game.TicTacToe;
import model.player.Player;

public class ResultController implements Controller {
    @FXML private Label lblResult;
    private TicTacToe ticTacToe;
    
    public ResultController(TicTacToe ticTacToe){
        this.ticTacToe = ticTacToe;
    }
    
    @FXML 
    protected void returnHomePage() throws IOException{
        App.setRoot("home");
    }

    @Override
    public void lazyInit() {
        Player winner = ticTacToe.getWinner();
        String result;
        if (winner != null){result = "Felicidades a " + winner.toString() + "!";} 
        else { result = "Empate";}
        
        lblResult.setText(result); 
        System.out.println(ticTacToe.board);
        System.out.println(result);
    }
    
}
