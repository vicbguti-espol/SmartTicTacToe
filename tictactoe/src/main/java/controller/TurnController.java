package controller;

import java.io.IOException;
import java.util.Queue;
import javafx.scene.control.Button;
import model.game.Computer;
import model.game.Human;
import model.game.Player;

public class TurnController extends ChooseController {
    SymbolController symbolController;
    Queue<Player> qPlayers;
        
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
                qPlayers.offer(new Human());
                qPlayers.offer(new Computer());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnComputer.setOnAction(e -> {
            try {
                App.setRoot("game", new GameController(this));
                qPlayers.offer(new Computer());
                qPlayers.offer(new Human());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
        btnContainer.getChildren().add(btnHuman);
        btnContainer.getChildren().add(btnComputer);
    }
    
}
