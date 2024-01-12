package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.board.Symbol;
import model.game.ComputerComputer;
import model.game.HumanComputer;
import model.game.HumanHuman;
import model.game.Impossible;
import model.game.Easy;
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
    @FXML
    Button btnStart;
    
    TicTacToe game;
    
    final String SYMBOL1_COLOR = "#4D4C7D";
    final String SYMBOL2_COLOR = "#F99417";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addModes();
        addSymbols();
        initDisable();
        
        cmbModes.setOnAction(e -> {
            if (cmbModes.getValue() instanceof HumanComputer){
                cmbSymbol1.setDisable(false);
                cmbSymbol2.setDisable(true);
            }
            
            if (cmbModes.getValue() instanceof HumanHuman){
                cmbSymbol1.setDisable(false);
                cmbSymbol2.setDisable(false);
                
                cmbSymbol2.setOnAction(eh -> {
                    if (cmbSymbol1.getValue() != null){
                        initCmbFirst();
                    }
                });
            }            
            
            if (cmbModes.getValue() instanceof ComputerComputer){
                cmbSymbol1.setDisable(true);
                cmbSymbol2.setDisable(true);
                
                game = (TicTacToe) cmbModes.getValue();
                
                final Symbol symbol1 = (Symbol) cmbSymbol1.getItems().get(0);
                final Symbol symbol2 = (Symbol) cmbSymbol2.getItems().get(1);
                    
                cmbSymbol1.setValue(symbol1);
                cmbSymbol2.setValue(symbol2);
                
                final Player player1 = game.getNext();
                final Player player2 = game.getNext();
                
                player1.setSymbol(symbol1);
                player2.setSymbol(symbol2);
                
                player1.setColor(SYMBOL1_COLOR);
                player2.setColor(SYMBOL2_COLOR);
                
                btnStart.setDisable(false);
            }
            
            cmbSymbol1.setOnAction(ep -> {
                
                if (cmbModes.getValue() instanceof HumanComputer){
                    Symbol s1 = (Symbol) cmbSymbol1.getValue();
                    cmbSymbol2.getItems().remove(s1);
                    cmbSymbol2.setValue(cmbSymbol2.getItems().get(0));
                }
                
                if (cmbSymbol2.getValue() != null){
                    initCmbFirst();
                }
                
            });
            
                
        });
        
    }

    private void initDisable() {
        cmbSymbol1.setDisable(true);
        cmbSymbol2.setDisable(true);
        cmbFirst.setDisable(true);
        btnStart.setDisable(true);
    }


    private void addSymbols() {
        cmbSymbol1.getItems().add(new Symbol('X'));
        cmbSymbol1.getItems().add(new Symbol('O'));
        
        cmbSymbol2.getItems().add(new Symbol('X'));
        cmbSymbol2.getItems().add(new Symbol('O'));
    }

    private void addModes() {
        cmbModes.getItems().add(new Easy());
        cmbModes.getItems().add(new Impossible());
        cmbModes.getItems().add(new ComputerComputer());
        cmbModes.getItems().add(new HumanHuman());
    }

    private void initCmbFirst() {
        cmbFirst.getItems().clear();
        game = (TicTacToe) cmbModes.getValue();
        
        Player p1 = game.getPlayer();
        Player p2 = game.getOponent();
        
        p1.setSymbol((Symbol) cmbSymbol1.getValue());
        p2.setSymbol((Symbol) cmbSymbol2.getValue());
        
        cmbFirst.getItems().add(p1);
        cmbFirst.getItems().add(p2);
        
        cmbFirst.setOnAction(el -> {
            game.players.clear();
            if (cmbFirst.getValue() != null){
                cmbFirst.setDisable(true);
                buildPlayerList();
                btnStart.setDisable(false);
            }
        });
        cmbFirst.setDisable(false);
    }

    private void buildPlayerList() {
        List<Player> copy = new ArrayList<>(cmbFirst.getItems());
        copy.remove(cmbFirst.getValue());
        
        final Player player1 = (Player) cmbFirst.getValue();
        final Player player2 = copy.get(0);
        
        player1.setColor(SYMBOL1_COLOR);
        player2.setColor(SYMBOL2_COLOR);
        
        game.players.offer(player1);
        game.players.offer(player2);
    }
    
    @FXML
    void startGame() throws IOException{
        game.setPlayers(game.players);
        System.out.println(game.players);
        App.setRoot("game", new GameController(game));
    }
}
