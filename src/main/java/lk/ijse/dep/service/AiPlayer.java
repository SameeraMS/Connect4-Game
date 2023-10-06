package lk.ijse.dep.service;

public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

//        do {
//            col = (int) (Math.random() * 6);
//        } while (!board.isLegalMove(col));

        col = this.bestMove();

        this.board.updateMove(col, Piece.GREEN);
        this.board.getBoardUI().update(col, false);

        Winner winner = this.board.findWinner();

        if (winner.getWinningPiece() != Piece.EMPTY){
            board.getBoardUI().notifyWinner(winner);        //notify winner
        } else if (!board.existLegalMoves()){               //if don't have legal move
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }

    private int bestMove() {

        boolean isUserWin = false;
        int tieCol = 0;
        int i;

        for (i=0; i<6; ++i){
            if (this.board.isLegalMove(i)){
                int row = this.board.findNextAvailableSpot(i);
                this.board.updateMove(i,Piece.GREEN);
                int heuristicVal = this.minimax(0,false);
                this.board.updateMove(i,row,Piece.EMPTY);
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

        if (isUserWin && this.board.isLegalMove(tieCol)){
            return tieCol;
        } else {
            boolean legal = false;

            do {
                i = (int)((Math.random() * 6));
            } while (!this.board.isLegalMove(i));
            return i;
        }
    }

    //minimax algorithm
    private int minimax(int depth, boolean maximizingPlayer) {
        Winner winner = this.board.findWinner();
        if (winner.getWinningPiece() == Piece.GREEN){
            return 1;
        } else if (winner.getWinningPiece() == Piece.BLUE){
            return -1;
        }else if (this.board.existLegalMoves() && depth != 2){
            int i;
            int row;
            int heuristicVal;

            if (!maximizingPlayer){
                for (i=0; i < 6; ++i){
                    if (this.board.isLegalMove(i)){
                        row = this.board.findNextAvailableSpot(i);
                        this.board.updateMove(i,Piece.BLUE);
                        heuristicVal = this.minimax(depth + 1,true);
                        this.board.updateMove(i,row,Piece.EMPTY);
                        if (heuristicVal == -1){
                            return heuristicVal;
                        }
                    }
                }
            } else {
                for (i=0; i < 6; ++i){
                    if (this.board.isLegalMove(i)){
                    row = this.board.findNextAvailableSpot(i);
                    this.board.updateMove(i,Piece.GREEN);
                    heuristicVal = this.minimax(depth + 1,false);
                    this.board.updateMove(i,row,Piece.EMPTY);
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
