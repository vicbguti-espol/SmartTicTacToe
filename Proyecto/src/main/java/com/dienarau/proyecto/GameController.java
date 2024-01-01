package com.dienarau.proyecto;

import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import minimax.Minimax;
import minimax.Symbol;
import minimax.TicTacToe;
import player.Bot;
import player.Human;
import player.Player;

public class GameController implements Initializable, Subscriber {

    @FXML
    private Button btnHome;
    @FXML
    private Label lblTurn;
    @FXML
    private GridPane gpBoard;
    
    private Queue<Player> playersTurn;
    
    private TicTacToe game;
    
    @FXML
    private Button btnTest;

    public GameController(){
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*playersTurn = new LinkedList<>();
        playersTurn.offer(new Human(new Symbol('X')));
        playersTurn.offer(new Bot(new Symbol('O')));*/
        playersTurn = game.getPlayers();
        lblTurn.setText(playersTurn.peek().toString());
        btnTest.setOnAction(e -> updateWinner());
        initBoard();
        if (playersTurn.peek() instanceof Bot) botTurn();
        
    }
    
    private void initBoard() {
        gpBoard.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        int i = 0;
        for (Node node: gpBoard.getChildren()) {
            Label lblBox = (Label) node;
            lblBox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            final int index = i;
            lblBox.setOnMouseClicked(eh -> {
                System.out.println("Clic en " + index + " box");
                
                if (lblBox.getText().isBlank()){
                    Player currentPlayer = playersTurn.poll();
                    
                    game.board.setSymbol(currentPlayer.getSymbol(), index);
                    
                    //game.board.boxes[index].setSymbol(currentPlayer.getSymbol());
                    
                    lblBox.setText(currentPlayer.getSymbol() + "");
                    playersTurn.offer(currentPlayer);
                    lblTurn.setText(currentPlayer.toString());
                    
                    if (playersTurn.peek() instanceof Bot) botTurn();
                    //botTurn();
                }
            });
            i++;
        }
    }
    
    private void botTurn(){
        //if (playersTurn.peek() instanceof Bot) {
            Player bot = playersTurn.poll();
            Minimax minimax = new Minimax(this.game);
            int bestMovement = minimax.calculate();
            game.board.setSymbol(bot.getSymbol(), bestMovement);
            //game.board.boxes[position].setSymbol(bot.getSymbol());
            playersTurn.offer(bot);
        //}
    }

    @Override
    public void update() {
        gpBoard.setDisable(true);
        btnTest.setDisable(true);
        popUpResult();
        
    }
    
    public void popUpResult() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
            Parent root = loader.load();
            
            /// pasar tictactoe al controlador de resultado

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Resultado");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
