package lk.ijse.dep.service;

public class Node extends Player{
    public Node(Board board) {
        super(board);
    }

    public static int bestMove(Board board) {

        boolean isUserWin = false;
        int tieCol = 0;
        int i;

        for (i=0; i<6; ++i){
            if (board.isLegalMove(i)){
                int row = board.findNextAvailableSpot(i);
                board.updateMove(i,Piece.GREEN);
                int heuristicVal = MctsAlgorithm.minimax(0,false,board);
                board.updateMove(i,row,Piece.EMPTY);
                if (heuristicVal == 1){
                    return i;
                }
                if (heuristicVal == -1){
                    isUserWin = true;
                } else {
                    tieCol =i;
                }
            }
        }

        if (isUserWin && board.isLegalMove(tieCol)){
            return tieCol;
        } else {
            boolean legal = false;

            do {
                i = (int)((Math.random() * 6));
            } while (!board.isLegalMove(i));
            return i;
        }
    }

}
