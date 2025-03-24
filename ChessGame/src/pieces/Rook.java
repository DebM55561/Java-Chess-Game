package pieces;
import main.Board;
import java.awt.image.BufferedImage;

public class Rook extends piece {
    public Rook(Board board, int col, int row, boolean isWhite) {
        super(board);
        this.col = col;
        this.row = row;
        this.Xpos = col * board.tileSize;
        this.Ypos = row * board.tileSize;
        this.isWhite = isWhite;
        this.name = "Rook";

        this.Sprite = sheet.getSubimage(4 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale).getScaledInstance(board.tileSize, board.tileSize, BufferedImage.SCALE_SMOOTH);

    }

    public boolean isValidMovement(int col, int row) {
        return this.row == row || this.col == col;
    }

    public boolean MoveCollide(int col, int row) {
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
        if (this.row > row) {
            for (int r = this.row - 1; r > row; r--) {
                if (board.getpiece(this.col, row) != null) {
                    return true;
                }
            }
        }
        if (this.row < row) {
            for (int r = this.row + 1; r < row; r++) {
                if (board.getpiece(this.col, row) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
