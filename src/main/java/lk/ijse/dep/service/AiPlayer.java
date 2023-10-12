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

        col = Node.bestMove(board);

        this.board.updateMove(col, Piece.GREEN);
        this.board.getBoardUI().update(col, false);

        Winner winner = this.board.findWinner();

        if (winner.getWinningPiece() != Piece.EMPTY){
            board.getBoardUI().notifyWinner(winner);        //notify winner
        } else if (!board.existLegalMoves()){               //if don't have legal move
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }




}
