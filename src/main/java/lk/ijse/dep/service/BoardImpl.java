package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board{
    private static Piece[][] pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;

        for (int i=0; i < NUM_OF_COLS; i++){
            for (int j=0; j < NUM_OF_ROWS; j++){
                //set empty to all
                pieces[i][j] = Piece.EMPTY;
            }
        }
    }

    @Override
    public BoardUI getBoardUI() {
        return this.boardUI;
    }

    @Override
    public int findNextAvailableSpot(int col) {
        //find next available row
        for (int i=0; i < NUM_OF_ROWS; i++){
            if (pieces[col][i] == Piece.EMPTY){
                //return next available row element
                return i;
            }
        }
        //if coloumn full return -1
        return -1;
    }

    @Override
    public boolean isLegalMove(int col) {
        if (findNextAvailableSpot(col) != -1 ){
            //if there is a available spot
            return true;
        } else {
            //if column is full
            return false;
        }
    }

    @Override
    public boolean existLegalMoves() {              //check there is a legal move
        for (int i =0; i < NUM_OF_COLS; i++){
            if (isLegalMove(i)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateMove(int col, Piece move) {
        pieces[col][findNextAvailableSpot(col)] = move;
    }

    @Override
    public void updateMove(int col, int row, Piece move) {      //minimax
        pieces[col][row] = move;
    }

    @Override
    public Winner findWinner() {

        //HumanPlayerWinner (colWinner check)
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length - 3; j++) {
                if (pieces[i][j] == Piece.BLUE & pieces[i][j + 1] == Piece.BLUE & pieces[i][j + 2] == Piece.BLUE & pieces[i][j + 3] == Piece.BLUE) {
                    int col1 = i;
                    int row1 = j;
                    int col2 = i;
                    int row2 = (j + 3);
                    return new Winner(Piece.BLUE, col1, row1, col2, row2);
                }
            }
        }
        //HumanPlayerWinner (rowWinner check)
        for (int i = 0; i < pieces.length - 3; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.BLUE & pieces[i + 1][j] == Piece.BLUE & pieces[i + 2][j] == Piece.BLUE & pieces[i + 3][j] == Piece.BLUE) {
                    int col1 = i;
                    int row1 = j;
                    int col2 = (i + 3);
                    int row2 = j;
                    return new Winner(Piece.BLUE, col1, row1, col2, row2);
                }
            }
        }
        //Ai_PlayerWinner (colWinner check)
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces[i].length - 3; j++) {
                if (pieces[i][j] == Piece.GREEN & pieces[i][j + 1] == Piece.GREEN & pieces[i][j + 2] == Piece.GREEN & pieces[i][j + 3] == Piece.GREEN) {
                    int col1 = i;
                    int row1 = j;
                    int col2 = i;
                    int row2 = (j + 3);
                    return new Winner(Piece.GREEN, col1, row1, col2, row2);
                }
            }
        }
        //Ai_PlayerWinner (rowWinner check)
        for (int i = 0; i < pieces.length - 3; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j] == Piece.GREEN & pieces[i + 1][j] == Piece.GREEN & pieces[i + 2][j] == Piece.GREEN & pieces[i + 3][j] == Piece.GREEN) {
                    int col1 = i;
                    int row1 = j;
                    int col2 = (i+3);
                    int row2 = j;
                    return new Winner(Piece.GREEN, col1, row1, col2, row2);
                }
            }
        }
        return new Winner(Piece.EMPTY);

    }

}
