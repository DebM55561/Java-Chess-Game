package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import pieces.*;


public class Board extends JPanel {

    public int tileSize = 100;
    public int row = 8;
    public int column = 8;
    public piece selectedPiece;
    public CheckScanner checkScanner = new CheckScanner(this);
    public int enPassantTile=-1;
    private boolean isWhiteToMove = true;
    private boolean isCheckMate = false;

    ArrayList<piece> piecelist = new ArrayList<piece>();
    input input = new input(this);
    public Board()
    {
        this.setPreferredSize(new Dimension(800, 800));
        this.setBackground(Color.cyan);
        addPieces();
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
    }

    public piece getpiece(int col, int row) {
        for (piece piece : piecelist) {
            if (piece.col == col && piece.row == row) {
                return piece;

            }
        }
        return null;
    }

    public  void MakeMove(Move move)
    {
        if(move.piece.name.equals("Pawn"))
        {

            MovePawn(move);
        }
        else if(move.piece.name.equals("King"))
        {
            MoveKing(move);
        }
        else {
            move.piece.col = move.NewCol;
            move.piece.row = move.NewRow;
            move.piece.Xpos = move.NewCol * tileSize;
            move.piece.Ypos = move.NewRow * tileSize;
            capture(move.capture);
            move.piece.isFirstMove=false;
        }
        isWhiteToMove=!isWhiteToMove;
        updateGameState();
    }

    private void updateGameState()
    {
        piece king = findKing(isWhiteToMove);
        if(checkScanner.CheckMate(king))
        {
            if(checkScanner.isKingChecked(new Move(this, king, king.col, king.row)))
            {
                System.out.println(isWhiteToMove? "Black Wins": "White Wins");
            }
            else
            {
                System.out.println("StaleMate");
            }

        }
    }

    private void MovePawn(Move move) {
        //enPassant
        int colIndex=move.piece.isWhite? 1:-1;
        if(GetTileNum(move.NewCol, move.NewRow)==enPassantTile)
        {
            move.capture=getpiece(move.NewCol, move.NewRow+colIndex);
        }
        if(Math.abs(move.piece.row-row)==2)
        {
            enPassantTile=GetTileNum(move.NewCol, move.NewRow+colIndex);
        }
        else
        {
            enPassantTile=-1;
        }
        colIndex = move.piece.isWhite? 0: 7;
        if(move.NewRow==colIndex)
        {
            Promote(move);
        }
        move.piece.col = move.NewCol;
        move.piece.row = move.NewRow;
        move.piece.Xpos = move.NewCol * tileSize;
        move.piece.Ypos = move.NewRow * tileSize;




    }

    private void MoveKing(Move move)
    {
        if(Math.abs(move.piece.col-move.NewCol)==2)
        {
            piece rook;
            if(move.piece.col<move.NewCol)
            {
                rook = getpiece(7, move.piece.row);
                rook.col=5;
            }
            else
            {
                rook = getpiece(0, move.piece.row);
                rook.col = 3;
            }
            rook.Xpos = rook.col*tileSize;
        }
        move.piece.col = move.NewCol;
        move.piece.row = move.NewRow;
        move.piece.Xpos = move.NewCol * tileSize;
        move.piece.Ypos = move.NewRow * tileSize;
        capture(move.capture);
        move.piece.isFirstMove=false;

    }

    private void Promote(Move move)
    {
        piecelist.add((new Queen(this, move.NewCol, move.NewRow, move.piece.isWhite)));
        capture(move.piece);
    }

    public boolean SameTeam(piece p1, piece p2)
    {
        if(p1 ==null || p2==null)
        {
            return false;
        }
        return p1.isWhite==p2.isWhite;
    }
    public boolean isValidMove(Move move)
    {
        if(move.piece.isWhite!=isWhiteToMove)
        {
            return false;
        }
        if(SameTeam(move.piece, move.capture))
        {
            return  false;
        }
        if(!move.piece.isValidMovement(move.NewCol, move.NewRow))
        {
            return false;
        }
        if(move.piece.MoveCollide(move.NewCol,move.NewRow))
        {
            return false;
        }
        if(checkScanner.isKingChecked(move))
        {
            return false;
        }
        return true;
    }

    public void capture(piece piece)
    {
        piecelist.remove(piece);
    }

    public piece findKing(boolean isWhite)
    {
        for(piece piece: piecelist)
        {
            if(isWhite == piece.isWhite && piece.name.equals("King"))
            {
                return piece;
            }
        }
        return null;
    }

    public int GetTileNum(int col, int row)
    {

        return row*this.row+col;
    }


    public void addPieces()
    {
//        BLACK PIECES
        piecelist.add(new Queen(this, 3,0, false));
        piecelist.add(new King(this, 4,0, false));
        piecelist.add(new Rook(this, 0,0, false));
        piecelist.add(new Rook(this, 7,0, false));
        piecelist.add(new Bishop(this, 2,0, false));
        piecelist.add(new Bishop(this, 5,0, false));
        piecelist.add(new Knight(this, 1,0, false));
        piecelist.add(new Knight(this, 6,0, false));

//        WHITE PIECES
        piecelist.add(new Queen(this, 4,7, true));
        piecelist.add(new King(this, 3,7, true));
        piecelist.add(new Rook(this, 0,7, true));
        piecelist.add(new Rook(this, 7,7, true));
        piecelist.add(new Bishop(this, 2,7, true));
        piecelist.add(new Bishop(this, 5,7, true));
        piecelist.add(new Knight(this, 1,7, true));
        piecelist.add(new Knight(this, 6,7, true));


//      BLACK PAWN
        piecelist.add(new pawn(this, 0,1, false));
        piecelist.add(new pawn(this, 1,1, false));
        piecelist.add(new pawn(this, 2,1, false));
        piecelist.add(new pawn(this, 3,1, false));
        piecelist.add(new pawn(this, 4,1, false));
        piecelist.add(new pawn(this, 5,1, false));
        piecelist.add(new pawn(this, 6,1, false));
        piecelist.add(new pawn(this, 7,1, false));

//        WHITE PAWN
        piecelist.add(new pawn(this, 0,6, true));
        piecelist.add(new pawn(this, 1,6, true));
        piecelist.add(new pawn(this, 2,6, true));
        piecelist.add(new pawn(this, 3,6, true));
        piecelist.add(new pawn(this, 4,6, true));
        piecelist.add(new pawn(this, 5,6, true));
        piecelist.add(new pawn(this, 6,6, true));
        piecelist.add(new pawn(this, 7,6, true));
    }


    public void paintComponent(Graphics g)
    {
        Graphics g2d = (Graphics2D) g;

        for(int r=0; r<row; r++)
        {
            for(int c=0; c<column; c++)
            {
                if((r+c)%2==0)
                {
                    g2d.setColor(new Color(78, 44, 156));
                }
                else
                {
                    g2d.setColor(new Color(149, 132, 227));
                }
                g2d.fillRect(c*tileSize, r*tileSize, tileSize, tileSize);
            }
        }

        if(selectedPiece!=null) {
            for (int r = 0; r < row; r++) {
                for (int c = 0; c < column; c++) {
                    if (isValidMove(new Move(this, selectedPiece, c, r))) {
                        g2d.setColor(new Color(0xB37DFD15, true));
                        g2d.fillRect(c * tileSize, r * tileSize, tileSize, tileSize);
                    }

                }
            }
        }

        for(piece piece : piecelist)
        {
            piece.paint(g2d);
        }
    }

}
