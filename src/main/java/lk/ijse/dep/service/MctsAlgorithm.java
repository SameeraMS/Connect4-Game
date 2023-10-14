package lk.ijse.dep.service;

public class MctsAlgorithm extends Player {
    public MctsAlgorithm(Board board) {
        super(board);
    }

    public static int minimax(int depth, boolean maximizingPlayer, Board board) {

        Winner winner = board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN){
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE){
            return -1;
        }else if (board.existLegalMoves() && depth != 2){
            int i;
            int row;
            int heuristicVal;

            if (!maximizingPlayer){
                for (i=0; i < 6; ++i){
                    if (board.isLegalMove(i)){
                        row = board.findNextAvailableSpot(i);
                        board.updateMove(i,Piece.BLUE);
                        heuristicVal = minimax(depth + 1,true, board);
                        board.updateMove(i,row,Piece.EMPTY);
                        if (heuristicVal == -1){
                            return heuristicVal;
                        }
                    }
                }
            } else {
                for (i=0; i < 6; ++i){
                    if (board.isLegalMove(i)){
                        row = board.findNextAvailableSpot(i);
                        board.updateMove(i,Piece.GREEN);
                        heuristicVal = minimax(depth + 1,false, board);
                        board.updateMove(i,row,Piece.EMPTY);
                        if (heuristicVal == 1){
                            return heuristicVal;
                        }
                    }
                }
            }
            return 0;
        } else {
            return 0;
        }
    }

}
