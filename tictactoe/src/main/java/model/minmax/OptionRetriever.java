package model.minmax;

//package model.minmax;
//
//import model.board.Box;
//import model.board.Board;
//
//public class OptionRetriever {
//    Board board;
//    Player player;
//    Player oponent;
//    Tree tree;
//    
//    public void buildTree(){
//        useBoard(board, player, tree);
//        
//        for (Tree dTree: tree.getChildren()){
//            useBoard(dTree.getContent(), oponent, dTree);
//        }
//    }
//    
//    public void useBoard(Board board, Player player, Tree tree){
//        for (int i = 0; i < board.ROWS * board.COLUMNS; i++){
//            Box box = board.boxes[i];
//            if (box.isEmpty()){
//                Board cBoard = new Board(board);
//                cBoard.boxes[i].setSymbol(player.getSymbol());
//                tree.getChildren().add(new Tree(cBoard));
//            }
//        }
//    }
//}
