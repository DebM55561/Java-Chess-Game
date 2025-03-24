package main;

import pieces.piece;

public class CheckScanner {
    Board board;

    public CheckScanner(Board board) {
        this.board = board;
    }

    public boolean isKingChecked(Move move) {
        piece King = board.findKing(move.piece.isWhite);
        assert King != null;
        int KingCol = King.col;
        int KingRow = King.row;

        if (board.selectedPiece != null && board.selectedPiece.name.equals("King")) {
            KingCol = move.NewCol;
            KingRow = move.NewRow;

        }
        return  HitByRook(move.NewCol, move.NewRow,King, KingCol, KingRow,0,1)||
                HitByRook(move.NewCol, move.NewRow,King, KingCol, KingRow,0,-1)||
                HitByRook(move.NewCol, move.NewRow,King, KingCol, KingRow,1,0)||
                HitByRook(move.NewCol, move.NewRow,King, KingCol, KingRow, -1,0)||

                HitByBishop(move.NewCol, move.NewRow,King, KingCol, KingRow, 1,1)||
                HitByBishop(move.NewCol, move.NewRow,King, KingCol, KingRow, 1,-1)||
                HitByBishop(move.NewCol, move.NewRow,King, KingCol, KingRow, -1,1)||
                HitByBishop(move.NewCol, move.NewRow,King, KingCol, KingRow, -1,-1)||

                HitByKnight(move.NewCol, move.NewRow, King, KingCol, KingRow)||
                HitByKing(King, KingCol, KingRow)||
                HitByPawn(move.NewCol,move.NewRow, King, KingCol, KingRow);

//    another condition to check for checks when king moves
    }



    private boolean HitByRook(int col, int row, piece king, int KingCol, int KingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (KingCol + (i * colVal) == col && KingRow + (i * rowVal) == row) {
                break;
            }
            piece piece = board.getpiece(KingCol + (i * colVal), KingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                if (!board.SameTeam(piece, king) && (piece.name.equals("Rook") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean HitByBishop(int col, int row, piece king, int KingCol, int KingRow, int colVal, int rowVal) {
        for (int i = 1; i < 8; i++) {
            if (KingCol - (i * colVal) == col && KingRow - (i * rowVal) == row) {
                break;
            }
            piece piece = board.getpiece(KingCol + (i * colVal), KingRow + (i * rowVal));
            if (piece != null && piece != board.selectedPiece) {
                if (!board.SameTeam(piece, king) && (piece.name.equals("Bishop") || piece.name.equals("Queen"))) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    private boolean HitByKnight(int col, int row, piece king, int KingCol, int KingRow) {
            return checkKnight(board.getpiece(KingCol + 1, KingRow + 2), king, col, row) ||
                    checkKnight(board.getpiece(KingCol - 1, KingRow + 2), king, col, row) ||
                    checkKnight(board.getpiece(KingCol - 1, KingRow - 2), king, col, row) ||
                    checkKnight(board.getpiece(KingCol + 1, KingRow - 2), king, col, row) ||
                    checkKnight(board.getpiece(KingCol + 2, KingRow + 1), king, col, row) ||
                    checkKnight(board.getpiece(KingCol + 2, KingRow - 2), king, col, row) ||
                    checkKnight(board.getpiece(KingCol - 2, KingRow - 1), king, col, row) ||
                    checkKnight(board.getpiece(KingCol - 2, KingRow + 1), king, col, row);
    }
    private boolean checkKnight(piece p, piece k, int col, int row) {
        return p != null && !board.SameTeam(p, k) && (p.name.equals("Knight")) && !(p.col == col && p.row == row);
    }

    private boolean CheckKing(piece k, piece p)
    {
        return p!=null & !board.SameTeam(p,k) && p.name.equals("King");
    }

    private boolean HitByPawn(int col, int row, piece king, int KingCol, int KingRow)
    {
        int colVal = king.isWhite? -1: 1;
        return CheckPawn(board.getpiece(KingCol+1, KingRow+colVal), king, col,row) ||
                CheckPawn(board.getpiece(KingCol-1, KingRow+colVal), king, col,row);

    }

    private boolean CheckPawn(piece p , piece k, int col, int row)
    {
        return p!=null && p.name.equals("Pawn") && !board.SameTeam(p, k) && !(p.col == col && p.row == row);
    }

    private boolean HitByKing(piece king, int Kingcol, int KingRow)
    {
        return CheckKing(king, board.getpiece(Kingcol-1, KingRow-1))||
             CheckKing(king, board.getpiece(Kingcol-1, KingRow))||
             CheckKing(king, board.getpiece(Kingcol-1, KingRow+1))||
             CheckKing(king, board.getpiece(Kingcol+1, KingRow-1))||
             CheckKing(king, board.getpiece(Kingcol+1, KingRow))||
             CheckKing(king, board.getpiece(Kingcol+1, KingRow+1))||
             CheckKing(king, board.getpiece(Kingcol, KingRow+1))||
             CheckKing(king, board.getpiece(Kingcol, KingRow-1));
    }

    public boolean CheckMate(piece king)
    {
        for(piece piece: board.piecelist)
        {
            if(board.SameTeam(piece, king))
            {
                board.selectedPiece = piece == king? king:null;
                for(int col=0; col<8; col++)
                {
                    for(int row=0; row<8; row++)
                    {
                        Move move = new Move(board, piece, col, row);
                        if(board.isValidMove(move))
                        {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}




