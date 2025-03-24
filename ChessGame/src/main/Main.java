package main;

import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setMinimumSize(new Dimension(300, 300));
    frame.setResizable(true);
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setTitle("Chess");
    Board board = new Board();
    frame.add(board);
    frame.pack(); 
    }
}