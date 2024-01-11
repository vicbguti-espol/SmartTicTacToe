package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.game.ComputerComputer;
import model.player.Player;
import model.game.TicTacToe;
import model.minimax.Minimax;
import model.player.Bot;

public class GameController implements Subscriber, Initializable {
    @FXML
    private Label lblTurn;
    @FXML
    private GridPane gpBoard;
    @FXML
    private Button btnMovement;
    
    public TicTacToe game;
    
    public GameController(){}
    
    public GameController(TicTacToe game){
        this.game = game;
    }
    
    public void drawBoard(){
        lblTurn.setText(game.players.peek().toString());
        gpBoard.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        
        for (int i = 0; i < gpBoard.getChildren().size(); i++){
            Label lblBox = (Label) gpBoard.getChildren().get(i);
            lblBox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            final int index = i;
            lblBox.setText(game.board.boxes[i].toString());
            lblBox.setOnMouseClicked(eh -> {
                if (lblBox.getText().isBlank()){
                    Player currentPlayer = game.getNext();
                    game.getBoard().setSymbol(currentPlayer.getSymbol(), index);
                    lblBox.setText(currentPlayer.getSymbol() + "");
                    
                    lblTurn.setText(currentPlayer.toString());
                    
                    if (!game.board.hasEnded && game.players.peek() instanceof Bot) botTurn();
                }
            });
        }
    }
    
    
    private void botTurn(){
        Player bot = game.getNext();
        
        gpBoard.setDisable(true);
        Minimax minimax = new Minimax(this.game);
        int bestMovement = minimax.calculate();
        
        game.getBoard().setSymbol(bot.getSymbol(), bestMovement);
        btnMovement.setDisable(false);
        btnMovement.setOnAction(e -> {
            TreeController controller = new TreeController(minimax.options);
            controller.setReturnController("game", this);
            try {
                App.setRoot("tree", controller);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        Label name = (Label) gpBoard.getChildren().get(bestMovement);
        name.setText(bot.getSymbol() + "");
        
        gpBoard.setDisable(false);
    }
    
    @FXML
    void returnHome() throws IOException{
        App.setRoot("choose");
    }

    @Override
    public void update() {
        gpBoard.setDisable(true);
        btnMovement.setDisable(true);
        try {
            App.setRoot("result", new ResultController(this.game));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.game.board.addSubscriber(this);
        this.btnMovement.setDisable(true);
        this.drawBoard();
        
        if (game.players.peek() instanceof Bot) botTurn();
        
        if (this.game instanceof ComputerComputer){
            btnMovement.setDisable(true);
            Thread taskThread = new Thread(new Runnable() {
            @Override
            public void run() {
              while(!game.board.hasEnded){
                try {
                  Thread.sleep(1000);
                } catch (InterruptedException e) {
                  e.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                  @Override
                  public void run() {
                    botTurn();
                  }
                });
              }
            }
          });

          taskThread.start();
        }
    }
}
