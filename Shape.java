import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents an abstract shape, with a partial method for drawing the shape
 * onto a Graphics context.
 *
 * @author
 * @version
 */
public abstract class Shape {

    private int positionX;
    private int positionY;
    private int sizeX;
    private int sizeY;
    private Color color;

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
    public Shape(int positionX, int positionY, int sizeX, int sizeY, Color color) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
    }

    /**
     * Getter for the x position of this shape.
     */
    public int getPositionX() {
        return this.positionX;
    }

    /**
     * Getter for the y position of this shape.
     */
    public int getPositionY() {
        return this.positionY;
    }

    /**
     * Getter for the size of this shape, in the x dimension.
     */
    public int getSizeX() {
        return this.sizeX;
    }

    /**
     * Getter for the size of this shape, in the y dimension.
     */
    public int getSizeY() {
        return this.sizeY;
    }

    /**
     * Setter method for the size of this shape, in both the x and y dimensions.
     */
    public void setSize(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    /**
     * Prepares to draw a visual representation of this shape.
     *
     * @param g - graphics context onto which we can draw
     */
    public void paintMe(Graphics g) {
        g.setColor(color);
    }
}
