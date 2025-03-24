package pieces;
import main.Board;
import java.awt.image.BufferedImage;

public class Queen extends piece {
    public Queen(Board board, int col, int row, boolean isWhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.Xpos = col*board.tileSize;
        this.Ypos = row*board.tileSize;
        this.isWhite = isWhite;
        this.name = "Queen";

        this.Sprite = sheet.getSubimage(sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row)
    {
        return Math.abs(this.row-row)==Math.abs(this.col-col) || this.row==row || this.col==col;
    }
    public boolean MoveCollide(int col, int row)
    {
       if(this.row==row) {
           if (this.col > col) {
               for (int c = this.col - 1; c > col; c--) {
                   if (board.getpiece(c, this.row) != null) {
                       return true;
                   }
               }
           }
           if (this.col < col) {
               for (int c = this.col + 1; c < col; c++) {
                   if (board.getpiece(c, this.row) != null) {
                       return true;
                   }
               }
           }
       }
       if (this.col == col)
       {
               if (this.row > row) {
                   for (int r = this.row - 1; r > row; r--) {
                       if (board.getpiece(this.col, r) != null) {
                           return true;
                       }
                   }
               }
               if (this.row < row) {
                   for (int r = this.row + 1; r < row; r++) {
                       if (board.getpiece(this.col, r) != null) {
                           return true;
                       }
                   }
               }
       }

           else {
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
