package main;

import pieces.piece;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class input extends MouseAdapter {
    Board board;

    public input(Board board)
    {
        this.board = board;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;
        piece pieceXY = board.getpiece(col, row);
        if(pieceXY!= null)
        {
            board.selectedPiece = pieceXY;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col = e.getX()/board.tileSize;
        int row = e.getY()/board.tileSize;
        if(board.selectedPiece!=null)
        {
            Move move = new Move(board, board.selectedPiece, col,row);
            if(board.isValidMove(move))
            {
                board.MakeMove(move);
            }
            else {
                board.selectedPiece.Xpos = board.selectedPiece.col*board.tileSize;
                board.selectedPiece.Ypos = board.selectedPiece.row*board.tileSize;
            }
        }
        board.selectedPiece=null;
        board.repaint();

    }


    @Override
    public void mouseDragged(MouseEvent e) {
        board.selectedPiece.Xpos = e.getX()-board.tileSize/2;
        board.selectedPiece.Ypos = e.getY()-board.tileSize/2;
        board.repaint();
    }
}
