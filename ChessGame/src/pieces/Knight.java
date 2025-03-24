package pieces;
import main.Board;
import main.Main;

import java.awt.image.BufferedImage;

public class Knight extends piece {
    public Knight(Board board, int col, int row, boolean isWhite)
    {
        super(board);
        this.col = col;
        this.row = row;
        this.Xpos = col*board.tileSize;
        this.Ypos = row*board.tileSize;
        this.isWhite = isWhite;
        this.name = "Knight";

        this.Sprite = sheet.getSubimage(3*sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row)
    {
        return Math.abs(col-this.col) * Math.abs(row-this.row)==2;
    }
}
