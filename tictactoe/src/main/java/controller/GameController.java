package controller;

import java.io.IOException;
import java.util.Queue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
        game.board.addSubscriber(this);
        lblTurn.setText(playersTurn.peek().toString());
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
                    Player currentPlayer = game.getNext();
                    game.getBoard().setSymbol(currentPlayer.getSymbol(), index);
                    lblBox.setText(currentPlayer.getSymbol() + "");
                    
                    lblTurn.setText(currentPlayer.toString());
                    
                    if (playersTurn.peek() instanceof Bot) botTurn();
                }
            });
            i++;
        }
    }
    
    private void botTurn(){
        gpBoard.setDisable(true);
        Minimax minimax = new Minimax(this.game);
        int bestMovement = minimax.calculate();
        Player bot = game.getNext();
        game.getBoard().setSymbol(bot.getSymbol(), bestMovement);
        Label name = (Label) gpBoard.getChildren().get(bestMovement);
        name.setText(bot.getSymbol() + "");
        gpBoard.setDisable(false);

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
    }
}
