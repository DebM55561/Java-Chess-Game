package pieces;
import main.Board;
import java.awt.image.BufferedImage;

public class Bishop extends piece {
    public Bishop(Board board, int col, int row, boolean isWhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.Xpos = col*board.tileSize;
        this.Ypos = row*board.tileSize;
        this.isWhite = isWhite;
        this.name = "Bishop";

        this.Sprite = sheet.getSubimage(2*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row)
    {
        return Math.abs(col-this.col)==Math.abs(row-this.row);
    }

    public boolean MoveCollide(int col, int row)
    {
        if(this.col>col && this.row>row)
        {
            for(int i=1; i<Math.abs(this.col-col); i++)
            {
                if(board.getpiece(this.col-i, this.row-i)!=null)
                {
                    return true;
                }
            }
        }

        if(this.col<col && this.row>row)
        {
            for(int i=1; i<Math.abs(this.col-col); i++)
            {
                if(board.getpiece(this.col+i, this.row-i)!=null)
                {
                    return true;
                }
            }
        }
        if(this.col>col && this.row<row)
        {
            for(int i=1; i<Math.abs(this.col-col); i++)
            {
                if(board.getpiece(this.col-i, this.row+i)!=null)
                {
                    return true;
                }
            }
        }

        if(this.col<col && this.row<row)
        {
            for(int i=1; i<Math.abs(this.col-col); i++)
            {
                if(board.getpiece(this.col+i, this.row+i)!=null)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
