package controller;

import dstructure.Tree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.board.Board;

public class TreeController implements Initializable {
    @FXML
    VBox root;
    @FXML
    HBox parentHBox;
    @FXML
    HBox childrenHBox;
    
    Tree<Board> tree;
    
    String returnFXML;
    Object returnController;
    
    public TreeController(Tree<Board> tree){
        this.tree = tree;
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        VBox parentVBox = (VBox) parentHBox.getChildren().get(0);
        GridPane parentGrid = (GridPane) parentVBox.getChildren().get(0);
        Label utility = (Label) parentVBox.getChildren().get(1);
        
        utility.setText("");
        
        Board rootBoard = tree.getContent();
        
        this.crowd(parentGrid, rootBoard);
        
        for (int i = 0; i < tree.getChildren().size(); i++){
            Tree subTree = (Tree) tree.getChildren().get(i);
            
            VBox childVBox = (VBox) childrenHBox.getChildren().get(i);
            GridPane childGrid = (GridPane) childVBox.getChildren().get(0);
            Label childUtility = (Label) childVBox.getChildren().get(1);
            
            Board childBoard = tree.getChildren().get(i).getContent();
            childUtility.setText("Utilidad: " + childBoard.getUtility());
            
            childVBox.setOnMouseClicked(eh -> {
                TreeController controller = new TreeController(subTree);
                controller.setReturnController("tree", this);
                try {
                    App.setRoot("tree", controller);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            
            });
            
            
            this.crowd(childGrid, childBoard);
        }
    }
    
    private void crowd(GridPane grid, Board board){;
        for (int j = 0; j < grid.getChildren().size(); j++){
            if (grid.getChildren().get(j) instanceof Label){
                Label lblBox = (Label) grid.getChildren().get(j) ;
                lblBox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
                lblBox.setText(board.boxes[j].toString());
            }
                
        }
    }
    
    @FXML
    public void returnBefore() throws IOException{
        App.scene.setRoot(App.loadFXML(returnFXML, returnController));
        if (returnController.getClass().equals(new GameController().getClass())){
            GameController gameController = (GameController) returnController;
            gameController.drawBoard();
        }
        
    }
    
    public void setReturnController(String returnFXML, Object returnController){
        this.returnFXML = returnFXML;
        this.returnController = returnController;
    } 
    
}
