package controller;

import dstructure.Tree;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.board.Board;
import model.board.Box;

public class TreeController implements Initializable {
    @FXML
    VBox root;
    @FXML
    HBox parentHBox;
    @FXML
    HBox childrenHBox;
    
    Tree<Board> tree;
    boolean minState;
    
    String returnFXML;
    Object returnController;
    
    public TreeController(Tree<Board> tree){
        this.tree = tree;
        this.minState = false;
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        crowdRoot();
        crowdChildren();
    }

    private void crowdRoot() {
        VBox parentVBox = (VBox) parentHBox.getChildren().get(0);
        GridPane parentGrid = (GridPane) parentVBox.getChildren().get(0);
        Label lblUtility = (Label) parentVBox.getChildren().get(1);
        lblUtility.setText("");
        Board rootBoard = tree.getContent();
        this.crowd(parentGrid, rootBoard);
    }

    private void crowdChildren() {
        int max = tree.getChildren().get(0).getContent().getUtility();
        VBox maxVBox = (VBox) childrenHBox.getChildren().get(0);
        for (int i = 0; i < tree.getChildren().size(); i++){
            Tree subTree = (Tree) tree.getChildren().get(i);
            VBox childVBox = (VBox) childrenHBox.getChildren().get(i);
            GridPane childGrid = (GridPane) childVBox.getChildren().get(1);
            Label childUtility = (Label) childVBox.getChildren().get(2);
            Board childBoard = tree.getChildren().get(i).getContent();
            crowd(childGrid, childBoard);

            final int utility = childBoard.getUtility();
            childUtility.setText("Utilidad: " + utility);
            if (!minState && max < utility){
                max = utility;
                maxVBox = childVBox; 
            } else if (minState && max > utility){
                max = utility;
                maxVBox = childVBox;
            }
            
            initVBox(childVBox, subTree);
        }
        
        maxVBox.setStyle("-fx-background-color: #F3F8FF;");
        Label lblState = (Label) maxVBox.getChildren().get(0);
        if (!minState) lblState.setText("Máximo");
        else lblState.setText("Mínimo");
    }


    private void initVBox(VBox childVBox, Tree subTree) {
        childVBox.setOnMouseClicked(eh -> {
            TreeController controller = new TreeController(subTree);
            controller.setReturnController("tree", this);
            controller.setMinState(true);
            try {
                App.setRoot("tree", controller);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    private void crowd(GridPane grid, Board board){
        for (int j = 0; j < grid.getChildren().size(); j++){
            if (grid.getChildren().get(j) instanceof Label){
                Label lblBox = (Label) grid.getChildren().get(j) ;
                final Box box = board.boxes[j];
                setSymbol(lblBox, box);
            }
                
        }
    }

    private void setSymbol(Label lblBox, final Box box) {
        if (box.symbol == null) return;
        lblBox.setStyle(lblBox.getStyle() + "-fx-text-fill:" + box.symbol.getColor());
        lblBox.setText(box.toString());
    }
    
    @FXML
    public void returnBefore() throws IOException{
        App.scene.setRoot(App.loadFXML(returnFXML, returnController));
        
    }
    
    public void setReturnController(String returnFXML, Object returnController){
        this.returnFXML = returnFXML;
        this.returnController = returnController;
    } 

    public boolean isMinState() {
        return minState;
    }

    public void setMinState(boolean minState) {
        this.minState = minState;
    }
    
    
    
}
