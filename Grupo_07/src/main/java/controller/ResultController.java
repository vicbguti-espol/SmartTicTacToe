package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.board.Board;
import model.board.Box;
import model.game.TicTacToe;
import model.player.Player;

public class ResultController implements Initializable {
    @FXML private Label lblResult;
    @FXML
    GridPane gpBoard;
    private TicTacToe ticTacToe;
    
    public ResultController(TicTacToe ticTacToe){
        this.ticTacToe = ticTacToe;
    }
    
    @FXML 
    protected void returnHomePage() throws IOException{
        App.setRoot("choose");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Player winner = ticTacToe.getWinner();
        if (winner != null)
            winner.setWins(winner.getWins() + 1);
        String result;
        if (winner != null){result = "Felicidades a " + winner.toString() + "!";} 
        else { result = "Empate";}
        
        lblResult.setText(result); 
        this.crowd(gpBoard, ticTacToe.board);
    }
    
    protected void crowd(GridPane grid, Board board) {
        for (int j = 0; j < grid.getChildren().size(); j++) {
            final Box box = board.boxes[j];
            if (grid.getChildren().get(j) instanceof Label) {
                Label lblBox = (Label) grid.getChildren().get(j);
                setSymbol(lblBox, box);
            }
        }
    }

    private void setSymbol(Label lblBox, final Box box) {
        if (box.symbol == null) return;
        lblBox.setStyle(lblBox.getStyle() + "-fx-text-fill:" + box.symbol.getColor());
        lblBox.setText(box.toString());
    }    
}
