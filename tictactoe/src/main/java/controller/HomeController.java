package controller;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeController implements Controller {
    
    @FXML
    private void switchGameMode() throws IOException {
        App.setRoot("choose", new GameModeController());
    }

    @Override
    public void lazyInit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
