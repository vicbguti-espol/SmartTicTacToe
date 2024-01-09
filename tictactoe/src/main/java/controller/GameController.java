package controller;

import java.io.IOException;
import java.util.Queue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.player.Player;
import model.game.TicTacToe;
import model.minimax.Minimax;
import model.player.Bot;
import view.BoardView;

public class GameController implements Subscriber, Controller {
    @FXML
    private Label lblTurn;
    @FXML
    private GridPane gpBoard;
    @FXML
    private Label lblP1;
    @FXML
    private Label lblP2;
    
    private Queue<Player> playersTurn;
    
    public TicTacToe game;
    
    TurnController turnController; 
    
    @FXML
    private Button btnMovement;
    
    private BoardView boardView;

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
        btnMovement.setDisable(true);
        this.playersTurn = turnController.qPlayers;
        System.out.println(playersTurn);
        this.game = new TicTacToe();
        game.setPlayers(playersTurn);
        game.board.addSubscriber(this);
        this.drawBoard();
        if (playersTurn.peek() instanceof Bot) botTurn();
    }
    
    public void drawBoard(){
        Player p1 = game.getNext();
        Player p2 = game.getNext();
        lblP1.setText(p1.toString() + " " + p1.wins);
        lblP2.setText(p2.toString() + " " + p2.wins);

        
        lblTurn.setText(playersTurn.peek().toString());
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
                    
                    if (playersTurn.peek() instanceof Bot) botTurn();
                }
            });
        }
    }
    
    
    private void botTurn(){
        System.out.println(playersTurn);
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
        System.out.println(playersTurn);
    }
    
    @FXML
    void returnHome() throws IOException{
        App.setRoot("home");
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
}
