import java.awt.*;

public class Circle extends Shape{

    private double velocityX;
    private double velocityY;
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
        this.velocityX = 0;
        this.velocityY = 0;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public void paintMe(Graphics g) {
        super.paintMe(g);
        g.fillOval(getPositionX(), getPositionY(), getSizeX(), getSizeY());
    }

    public void move() {
        this.setPositionX((int) (this.getPositionX() + velocityX));
        this.setPositionY((int) (this.getPositionY() + velocityY));
    }
}
