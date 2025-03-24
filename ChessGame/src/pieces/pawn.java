package pieces;
import main.Board;
import java.awt.image.BufferedImage;

public class pawn extends piece {
    public pawn(Board board, int col, int row, boolean isWhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.Xpos = col*board.tileSize;
        this.Ypos = row*board.tileSize;
        this.isWhite = isWhite;
        this.name = "Pawn";
        int FirstMove=1;
        this.Sprite = sheet.getSubimage(5*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }


    public boolean isValidMovement(int col, int row)
    {
        int colIndex= isWhite? 1: -1;

        //push
        if(this.col==col && this.row-colIndex==row && board.getpiece(this.col, this.row)!=null)
        {
            return true;
        }
        //push 2
        if(isFirstMove && this.col==col && row==this.row-colIndex*2 && board.getpiece(col, row-colIndex)==null)
        {
            return true;
        }
        //left capture
        if(col==this.col-1 && row==this.row-colIndex && board.getpiece(col, row)!=null)
        {
            return true;
        }
        //right capture
        if(col==this.col+1 && row==this.row-colIndex && board.getpiece(col, row)!=null)
        {
            return true;
        }
        return false;
    }
    public boolean MoveCollide(int col, int row)
    {
        int colIndex = isWhite? -1:1;
        if(this.col==col)
        {
            if(board.getpiece(this.col, this.row+colIndex)!=null)
            {
                return true;
            }
        }
        return false;
    }


}
