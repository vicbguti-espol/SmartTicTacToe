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
import model.board.Box;
import model.game.ComputerComputer;
import model.game.HumanComputer;
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
    private Minimax minimax;
    
    public GameController(){}
    
    public GameController(TicTacToe game){
        this.game = game;
    }
    
    public void drawBoard(){
        setTurnLabel(game.players.peek());
        
        for (int i = 0; i < gpBoard.getChildren().size(); i++){
            Label lblBox = (Label) gpBoard.getChildren().get(i);
            final int index = i;
            final Box box = game.board.boxes[i];
            setSymbol(lblBox, box);
            lblBox.setOnMouseClicked(eh -> {
                if (lblBox.getText().isBlank()){
                    Player currentPlayer = game.getNext();
                    setSymbol(currentPlayer, index, lblBox);
                    setTurnLabel(game.players.peek());
                    if (game.players.peek() instanceof Bot) botTurn();
                }
            });
        }
    }

    private void setSymbol(Label lblBox, final Box box) {
        if (box.symbol == null) return;
        lblBox.setStyle(lblBox.getStyle() + "-fx-text-fill:" + box.symbol.getColor());
        lblBox.setText(box.toString());
    }

    private void setSymbol(Player currentPlayer, final int index, Label lblBox) {
        game.getBoard().setSymbol(currentPlayer.getSymbol(), index);
        lblBox.setStyle(lblBox.getStyle() + "-fx-text-fill:" + currentPlayer.getColor());
        lblBox.setText(currentPlayer.getSymbol() + "");
    }

    private void setTurnLabel(Player player) {
        lblTurn.setStyle("-fx-text-fill:" + player.getColor());
        lblTurn.setText(player.toString());
    }
    
    
    private void botTurn(){
        Player bot = game.getNext();
        setTurnLabel(game.players.peek());
        
        gpBoard.setDisable(true);
        btnMovement.setDisable(false);
        
        minimax = new Minimax(this.game);
        iBtnMovement();
        
        int bestMovement = minimax.calculate();
        Label name = (Label) gpBoard.getChildren().get(bestMovement);
        setSymbol(bot, bestMovement, name);
        gpBoard.setDisable(false);
    }

    private void iBtnMovement() {
        btnMovement.setOnAction(e -> {
            TreeController controller = new TreeController(minimax.options);
            controller.setReturnController("game", this);
            try {
                App.setRoot("tree", controller);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
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
        final boolean nextBot = game.players.peek() instanceof Bot;
        this.game.board.addSubscriber(this);
        
        
        if (game instanceof HumanComputer){
            if (!game.board.isEmpty()){
                btnMovement.setDisable(false);
                iBtnMovement();
            } else{
                btnMovement.setDisable(true);
            }
        }
        
        
        this.drawBoard();
        if (nextBot) botTurn();
        
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
