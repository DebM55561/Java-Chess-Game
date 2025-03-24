package pieces;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import main.Board;


public class piece {
    public int col, row;
    public int Xpos, Ypos;

    public boolean isWhite;
    public String name;
    public int value;
    public boolean isFirstMove=true;


    BufferedImage sheet;
    {
        try{
            sheet=ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("pieces.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Image Sprite;
    Board board;
    protected int sheetScale = sheet.getWidth()/6;

    public piece(Board board)
    {
        this.board = board;
    }

    public boolean isValidMovement(int col, int row){
        return  false;
    }
    public boolean MoveCollide(int col, int row){
        return  false;
    }

    public void paint(Graphics g2d)
    {
        g2d.drawImage(Sprite, Xpos, Ypos, null);
    }
}
