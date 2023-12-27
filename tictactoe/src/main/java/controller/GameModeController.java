package controller;

import java.io.IOException;
import javafx.scene.control.Button;
import model.game.TicTacToe;

public class GameModeController extends ChooseController {  
    Button btnHumanComputer;
    
    public GameModeController(){
        btnHumanComputer = new Button("Humano VS Computadora");
        btnHumanComputer.setUserData(new TicTacToe());
        btnHumanComputer.setOnAction(e -> {
            try {
                App.setRoot("choose", new SymbolController(this));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
    }
    
    @Override
    public void lazyInit()  {
        btnContainer.getChildren().add(btnHumanComputer);
        lblOption.setText("Seleccionar el modo de juego");
    }
}
