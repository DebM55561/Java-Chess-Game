package pieces;
import main.Board;
import main.Move;

import java.awt.image.BufferedImage;

public class King extends piece {
    public King(Board board, int col, int row, boolean isWhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.Xpos = col*board.tileSize;
        this.Ypos = row*board.tileSize;
        this.isWhite = isWhite;
        this.name = "King";

        this.Sprite = sheet.getSubimage(0, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row)
    {
        if(Math.abs((this.col-col)*(this.row-row))==1)
        {
            return true;
        }
        if(col==this.col && Math.abs(row-this.row)==1)
        {
            return true;
        }
        if(row==this.row && Math.abs(col-this.col)==1)
        {
            return true;
        }
        if(CanCastle(col, row))
        {
            return true;
        }
        return false;
    }

    private boolean CanCastle(int col, int row)
    {
        if(this.row==row)
        {
            if(col==6)
            {
                piece rook = board.getpiece(7, row);
                if(rook!=null && rook.isFirstMove && isFirstMove)
                {
                    return board.getpiece(5, row)==null &&
                            board.getpiece(6, row)==null &&
                            !board.checkScanner.isKingChecked(new Move(board, this, 5, row));
                }
            }
            else if(col==2)
            {
                piece rook = board.getpiece(0, row);
                if(rook!=null && rook.isFirstMove && isFirstMove)
                {
                    return board.getpiece(3, row)==null &&
                            board.getpiece(2, row)==null &&
                            board.getpiece(1, row)==null &&
                            !board.checkScanner.isKingChecked(new Move(board, this, 3, row));
                }
            }
        }
        return false;
    }
}
