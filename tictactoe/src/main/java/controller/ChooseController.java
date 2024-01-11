package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.board.Symbol;
import model.game.ComputerComputer;
import model.game.HumanComputer;
import model.game.HumanHuman;
import model.game.Impossible;
import model.game.Medium;
import model.game.TicTacToe;
import model.player.Player;

public class ChooseController implements Initializable {
    @FXML
    ComboBox cmbModes;
    @FXML
    ComboBox cmbSymbol1;
    @FXML
    ComboBox cmbSymbol2;
    @FXML
    ComboBox cmbFirst;
    
    TicTacToe game;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbModes.getItems().add(new Medium());
        cmbModes.getItems().add(new Impossible());
        cmbModes.getItems().add(new ComputerComputer());
        cmbModes.getItems().add(new HumanHuman());
        
        cmbSymbol1.getItems().add(new Symbol('X'));
        cmbSymbol1.getItems().add(new Symbol('O'));
        
        cmbSymbol2.getItems().add(new Symbol('X'));
        cmbSymbol2.getItems().add(new Symbol('O'));
        
        cmbSymbol1.setDisable(true);
        cmbSymbol2.setDisable(true);
        cmbFirst.setDisable(true);
        
        cmbModes.setOnAction(e -> {
            if (cmbModes.getValue() instanceof HumanComputer){
                cmbSymbol1.setDisable(false);
                cmbSymbol2.setDisable(true);
            }
            
            if (cmbModes.getValue() instanceof HumanHuman){
                cmbSymbol1.setDisable(false);
                cmbSymbol2.setDisable(false);
            }            
            
            if (cmbModes.getValue() instanceof ComputerComputer){
                cmbSymbol1.setDisable(true);
                cmbSymbol2.setDisable(true);
                
                game = (TicTacToe) cmbModes.getValue();
                    
                game.getNext().setSymbol((Symbol) cmbSymbol1.getItems().get(0));
                game.getNext().setSymbol((Symbol) cmbSymbol2.getItems().get(1));
            }
            
            cmbSymbol1.setOnAction(ep -> {
                
                if (cmbModes.getValue() instanceof HumanComputer){
                    Symbol s1 = (Symbol) cmbSymbol1.getValue();
                    cmbSymbol2.getItems().remove(s1);
                    cmbSymbol2.setValue(cmbSymbol2.getItems().get(0));
                }
                
                if (cmbSymbol2.getValue() != null){
                    game = (TicTacToe) cmbModes.getValue();
                    
                    Player p1 = game.players.poll();
                    Player p2 = game.players.poll();

                    p1.setSymbol((Symbol) cmbSymbol1.getValue());
                    p2.setSymbol((Symbol) cmbSymbol2.getValue());

                    cmbFirst.getItems().add(p1);
                    cmbFirst.getItems().add(p2);
                    
                    cmbFirst.setOnAction(el -> {
                        if (cmbFirst.getValue() != null){
                            cmbFirst.setDisable(true);
                            game.players.offer((Player) cmbFirst.getValue());
                            List<Player> copy = new ArrayList<>(cmbFirst.getItems());
                            copy.remove(cmbFirst.getValue());
                            game.players.offer(copy.get(0));
                        } 
                    });
                    cmbFirst.setDisable(false);
                }
                
            });
            
                
        });
        
    }
    
    @FXML
    void startGame() throws IOException{
        game.setPlayers(game.players);
        System.out.println(game.players);
        App.setRoot("game", new GameController(game));
    }
}
