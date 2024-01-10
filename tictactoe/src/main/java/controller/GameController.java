package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.player.Player;
import model.game.TicTacToe;
import model.minimax.Minimax;
import model.player.Bot;

public class GameController implements Subscriber, Controller, Initializable {
    @FXML
    private Label lblTurn;
    @FXML
    private GridPane gpBoard;
    @FXML
    private Label lblP1;
    @FXML
    private Label lblP2;
    
    public TicTacToe game;

    @FXML
    private Button btnMovement;

    public GameController(){}
    
    public GameController(TicTacToe game){
        this.game = game;
    }
    
    @Override
    public void lazyInit() {
        game.board.addSubscriber(this);
        btnMovement.setDisable(true);
        // this.playersTurn = turnController.qPlayers;
        // System.out.println(playersTurn);
        // this.game = new TicTacToe();
        // game.setPlayers(playersTurn);
        this.drawBoard();
        while (game.players.peek() instanceof Bot && !game.board.hasEnded){
            botTurn();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        } 
        // if (game.players.peek() instanceof Bot) botTurn();
    }
    
    public void drawBoard(){ 
        System.out.println(game.players);
        lblTurn.setText(game.players.peek().toString());
        gpBoard.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        
        for (int i = 0; i < gpBoard.getChildren().size(); i++){
            Label lblBox = (Label) gpBoard.getChildren().get(i);
            lblBox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            final int index = i;
            lblBox.setText(game.board.boxes[i].toString());
            lblBox.setOnMouseClicked(eh -> {
                System.out.println("Clic en " + index + " box");
                
                if (lblBox.getText().isBlank()){
                    Player currentPlayer = game.getNext();
                    game.getBoard().setSymbol(currentPlayer.getSymbol(), index);
                    lblBox.setText(currentPlayer.getSymbol() + "");
                    
                    lblTurn.setText(currentPlayer.toString());
                    
                    if (game.players.peek() instanceof Bot) botTurn();
                }
            });
        }
    }
    
    
    private void botTurn(){
        gpBoard.setDisable(true);
        Minimax minimax = new Minimax(this.game);
        int bestMovement = minimax.calculate();
        Player bot = game.getNext();
        
        
        
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
        System.out.println(game.board);
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
        
        
    }
}
