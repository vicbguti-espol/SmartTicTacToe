package controller;

import java.io.IOException;
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
import model.player.Player;
import model.game.TicTacToe;
import model.minimax.Minimax;
import model.player.Bot;

public class GameController implements Subscriber, Controller {

    @FXML
    private Button btnHome;
    @FXML
    private Label lblTurn;
    @FXML
    private GridPane gpBoard;
    
    private Queue<Player> playersTurn;
    
    private TicTacToe game;
    
    TurnController turnController;
    
    @FXML
    private Button btnTest;

    public GameController(){
        
    }
    
    public GameController(TurnController aThis) {
        this.turnController = aThis;
    }
    
    public void setTurnController(TurnController aThis) {
        this.turnController = aThis;
    }
    
    @Override
    public void lazyInit() {
        this.playersTurn = turnController.qPlayers;
        this.game = new TicTacToe();
        game.setPlayers(playersTurn);
        /*playersTurn = new LinkedList<>();
        playersTurn.offer(new Human(new Symbol('X')));
        playersTurn.offer(new Bot(new Symbol('O')));*/
        playersTurn = game.getPlayers();
        lblTurn.setText(playersTurn.peek().toString());
        btnTest.setOnAction(e -> update());
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
                    
                    game.getBoard().setSymbol(currentPlayer.getSymbol(), index);
                    
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
            gpBoard.setDisable(true);
            Minimax minimax = new Minimax(this.game);
            int bestMovement = minimax.calculate();
            Player bot = playersTurn.poll();
            game.getBoard().setSymbol(bot.getSymbol(), bestMovement);
            Label name = (Label) gpBoard.getChildren().get(bestMovement);
            name.setText(bot.getSymbol() + "");
            playersTurn.offer(bot);
            gpBoard.setDisable(false);
        //}
    }

    @Override
    public void update() {
        gpBoard.setDisable(true);
        btnTest.setDisable(true);
        try {
            App.setRoot("result", new ResultController(this.game));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //popUpResult();
        
    }
    
    public void popUpResult() {
        System.out.println("GANO ALGUIEN");
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
