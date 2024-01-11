package view;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.board.Board;

public class BoardView {
    public Board board;
    public VBox vBox;
    public GridPane gridPane;
    private boolean utility;
    
    public BoardView(Board board){
        this.board = board;
        System.out.println(board);
        // this.vBox = new VBox();
        // this.gridPane = new GridPane();
        // this.utility = true;
        // this.buildGrid();
    }

    public void setGridPane(GridPane gridPane) {
        for (int i = 0; i < gridPane.getChildren().size() - 1; i++){
            Label lblBox = (Label) gridPane.getChildren().get(i);
            lblBox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
            lblBox.setText(board.boxes[i].toString());   
        }
    }
    
    public void buildGrid(){
        board.map((box) -> {
            Label lblBox = new Label(box.toString()); 
            gridPane.add(lblBox, 
                    box.getPosY(), 
                    box.getPosX());
        });
        
        vBox.getChildren().clear();
        vBox.getChildren().add(gridPane);
        
        if (utility){
            vBox.getChildren().add(new Label(String.valueOf(board.getUtility())));
        }
    }

    public void setUtility(boolean utility) {
        this.utility = utility;
    }
    
    
}
