package lk.ijse.dep.service;

public class AiPlayer extends Player {
    public AiPlayer(Board board) {
        super(board);
    }

    @Override
    public void movePiece(int col) {

        do {
            col = (int) (Math.random() * 6);
        } while (!board.isLegalMove(col));

        board.updateMove(col, Piece.GREEN);
        board.getBoardUI().update(col, false);

        Winner winner = board.findWinner();

        if (winner.getWinningPiece() != Piece.EMPTY){
            board.getBoardUI().notifyWinner(winner);
        } else if (!board.existLegalMoves()){
            board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));
        }
    }

        //minimax algorithem
}
