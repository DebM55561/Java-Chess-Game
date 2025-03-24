package main;
import pieces.piece;

public class Move {

    int OldCol;
    int OldRow;
    int NewCol;
    int NewRow;

    piece piece;
    piece capture;

    public Move(Board board, piece piece, int newCol, int newRow)
    {
        this.OldCol= piece.col;
        this.OldRow=piece.row;
        this.NewCol=newCol;
        this.NewRow=newRow;
        this.piece=piece;
        this.capture=board.getpiece(newCol, newRow);
    }
}
