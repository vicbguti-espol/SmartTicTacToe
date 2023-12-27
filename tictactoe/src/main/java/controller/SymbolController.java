package controller;

import java.io.IOException;
import javafx.scene.control.Button;
import model.board.Symbol;
import model.game.Player;

public class SymbolController extends ChooseController {
    GameModeController gameModeController;
    Player player;
    
    public SymbolController(GameModeController gameModeController){
        this.gameModeController = gameModeController;
        this.player = new Player();
    }
    
    @Override
    public void lazyInit() {
        lblOption.setText("Seleccionar su símbolo");
        Button btnX = new Button("X");
        Button btnO = new Button("O");
        
        btnX.setUserData(new Symbol('X'));
        btnO.setUserData(new Symbol('O'));
        
        for (Button btn: new Button[]{btnX, btnO}){
            btn.setOnAction(e -> {
                player.setSymbol((Symbol) btn.getUserData());
                try {
                    App.setRoot("choose", new TurnController(this));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            
            btnContainer.getChildren().add(btn);
        }
    }
    
}
