package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController implements Controller {
    @FXML Button primaryButton;
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void lazyInit() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
