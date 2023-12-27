package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class ChooseController implements Controller {
    @FXML HBox btnContainer;
    @FXML Label lblOption;
    
    @FXML 
    protected void returnHomePage() throws IOException{
        App.setRoot("home");
    }
}
