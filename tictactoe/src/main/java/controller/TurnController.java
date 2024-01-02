package controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import javafx.scene.control.Button;
import model.player.Player;

public class TurnController extends ChooseController {
    private SymbolController symbolController;
    public Queue<Player> qPlayers;
    GameController gameController;
        
    TurnController(SymbolController symbolController){
        this.symbolController = symbolController;
        this.gameController = new GameController();
        
        qPlayers = new LinkedList<>();
    }
            
    @Override
    public void lazyInit() {
        lblOption.setText("¿Quién deseas que empiece?");
        Button btnHuman = new Button("Humano");
        Button btnComputer = new Button("Computador");
        
        btnHuman.setOnAction(e -> {
            try {
                qPlayers.offer(symbolController.human);
                qPlayers.offer(symbolController.computer);
                App.setRoot("game", new GameController(this));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnComputer.setOnAction(e -> {
            try {
                qPlayers.offer(symbolController.computer);
                qPlayers.offer(symbolController.human);
                App.setRoot("game", new GameController(this));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnContainer.getChildren().add(btnHuman);
        btnContainer.getChildren().add(btnComputer);
    }
    
}
