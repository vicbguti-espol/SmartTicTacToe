package controller;

import java.io.IOException;
import javafx.scene.control.Button;
import model.board.Symbol;
import model.player.Computer;
import model.player.Human;
import model.player.Player;

public class SymbolController extends ChooseController {
    private GameModeController gameModeController;
    Player human;
    Player computer;
    
    public SymbolController(GameModeController gameModeController){
        this.gameModeController = gameModeController;
        this.human = new Human();
        this.computer = new Computer();
    }
    
    @Override
    public void lazyInit() {
        lblOption.setText("Seleccionar su símbolo");
        Button btnX = new Button("X");
        Button btnO = new Button("O");
        
        btnX.setUserData(new Symbol('X'));
        btnO.setUserData(new Symbol('O'));
        
        btnX.setOnAction(e -> {
            human.setSymbol((Symbol) btnX.getUserData());
            computer.setSymbol((Symbol) btnO.getUserData());
            try {
                App.setRoot("choose", new TurnController(this));
            } catch (IOException ex) {
                ex.printStackTrace();
            }            
        });
        
        btnO.setOnAction(e -> {
            human.setSymbol((Symbol) btnO.getUserData());
            computer.setSymbol((Symbol) btnX.getUserData());
            try {
                App.setRoot("choose", new TurnController(this));
            } catch (IOException ex) {
                ex.printStackTrace();
            }            
        });
        
        btnContainer.getChildren().add(btnX);
        btnContainer.getChildren().add(btnO);
    }
    
}
