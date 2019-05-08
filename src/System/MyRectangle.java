package System;

import javax.swing.*;
import java.awt.*;

public class MyRectangle extends JComponent {
    private int posX;
    private int posY;
    private int rectWidth;
    private int rectHeight;
    private Color color;

    public MyRectangle(int posX, int posY, int rectWidth, int rectHeight, Color color) {
        this.posX = posX;
        this.posY = posY;
        this.rectWidth = rectWidth;
        this.rectHeight = rectHeight;
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.drawRect (posX, posY, rectWidth, rectHeight);
        g.fillRect (posX, posY, rectWidth, rectHeight);
    }
}
