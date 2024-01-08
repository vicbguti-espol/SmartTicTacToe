package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.board.Board;

public class BoardView {
    public Board board;
    public VBox vBox;
    public GridPane gridPane;
    
    public BoardView(Board board){
        vBox = new VBox();
        gridPane = new GridPane();
        gridPane.setStyle("-fx-border-color: black; -fx-border-width: 1px;");
        
        board.map((box) -> {
            Label lblBox = new Label(box.toString()); 
            // lblBox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            gridPane.add(lblBox, 
                    box.getPosY(), 
                    box.getPosX());
        });
        
        vBox.getChildren().add(gridPane);
        vBox.getChildren().add(new Label(String.valueOf(board.getUtility())));
    }
    
    
}
