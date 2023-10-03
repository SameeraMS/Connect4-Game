package lk.ijse.dep.service;

import lk.ijse.dep.controller.BoardController;

public class BoardImpl implements Board{
    private static Piece[][] pieces = new Piece[NUM_OF_COLS][NUM_OF_ROWS];
    private BoardUI boardUI;

    public BoardImpl(BoardUI boardUI) {
        this.boardUI = boardUI;

        for (int i=0; i < NUM_OF_COLS; i++){
            for (int j=0; j < NUM_OF_ROWS; j++){
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
            //there is no raw
            return false;
        }
    }

    @Override
    public boolean existLegalMoves() {
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
    public void updateMove(int col, int row, Piece move) {

    }

    @Override
    public Winner findWinner() {

        for (int i=0; i<NUM_OF_ROWS; i++){
            for (int j=0; j<NUM_OF_COLS/2; j++){
                if((!pieces[j][i].equals(Piece.EMPTY)) && pieces[j][i].equals(pieces[j+1][i]) && pieces[j][i].equals(pieces[j+2][i]) && pieces[j][i].equals(pieces[j+3][i])){
                    return new Winner(pieces[j][i],j,i,j+3,i);
                }
            }
        }
        for (int j=0; j<NUM_OF_COLS; j++){
            for (int i=0; i<NUM_OF_ROWS/2; i++){
                if (!pieces[j][i].equals(Piece.EMPTY) && pieces[j][i].equals(pieces[j][i+1]) && pieces[j][i].equals(pieces[j][i+2]) && pieces[j][i].equals(pieces[j][i+3])){
                    return new Winner(pieces[j][i],j,i,j,i+3);
                }
            }
        }
        return new Winner(Piece.EMPTY);
    }

}
