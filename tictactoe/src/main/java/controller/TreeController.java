package controller;

import dstructure.Tree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.board.Board;
import view.BoardView;

public class TreeController implements Controller, Initializable {
    @FXML
    VBox root;
    @FXML
    HBox parentHBox;
    @FXML
    HBox childrenVBox;
    
    Tree<Board> tree;
    
    String returnFXML;
    Controller returnController;
    
    public TreeController(Tree<Board> tree){
        this.tree = tree;
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        parentHBox.getChildren().add(new BoardView(tree.getContent()).vBox);
        
        for (Tree<Board> subTree: tree.getChildren()){
            VBox vbox = new BoardView(subTree.getContent()).vBox;
            vbox.setOnMouseClicked(eh -> {
                TreeController controller = new TreeController(subTree);
                controller.setReturnController("tree", this);
                try {
                    App.setRoot("tree", controller);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            childrenVBox.getChildren().add(vbox);
        } 
    }

    @Override
    public void lazyInit() {
        
    }
    
    @FXML
    public void returnBefore() throws IOException{
        App.scene.setRoot(App.loadFXML(returnFXML, returnController));
        if (returnController.getClass().equals(new GameController().getClass())){
            GameController gameController = (GameController) returnController;
            gameController.drawBoard();
        }
        
    }
    
    public void setReturnController(String returnFXML, Controller returnController){
        this.returnFXML = returnFXML;
        this.returnController = returnController;
    } 
    
}
