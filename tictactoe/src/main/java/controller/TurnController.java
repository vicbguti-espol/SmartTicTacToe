package controller;

import java.io.IOException;
import java.util.Queue;
import javafx.scene.control.Button;
import model.player.Player;

public class TurnController extends ChooseController {
    private SymbolController symbolController;
    private Queue<Player> qPlayers;
        
    TurnController(SymbolController symbolController){
        this.symbolController = symbolController;
    }
            
    @Override
    public void lazyInit() {
        lblOption.setText("¿Quién deseas que empiece?");
        Button btnHuman = new Button("Humano");
        Button btnComputer = new Button("Computador");
        
        btnHuman.setOnAction(e -> {
            try {
                App.setRoot("game", new GameController(this));
                qPlayers.offer(symbolController.human);
                qPlayers.offer(symbolController.computer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnComputer.setOnAction(e -> {
            try {
                App.setRoot("game", new GameController(this));
                qPlayers.offer(symbolController.computer);
                qPlayers.offer(symbolController.human);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnContainer.getChildren().add(btnHuman);
        btnContainer.getChildren().add(btnComputer);
    }
    
}
