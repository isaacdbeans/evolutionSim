import java.awt.*;

public class Circle extends Shape{
    /**
     * Creates a shape for drawing, with given x and y position, size in each
     * dimension, and color.
     *
     * @param positionX
     * @param positionY
     * @param sizeX
     * @param sizeY
     * @param color
     */
    public Circle(int positionX, int positionY, int sizeX, int sizeY, Color color) {
        super(positionX, positionY, sizeX, sizeY, color);
    }

    public void paintMe(Graphics g) {
        super.paintMe(g);
        g.fillOval(getPositionX(), getPositionY(), getSizeX(), getSizeY());
    }
}
