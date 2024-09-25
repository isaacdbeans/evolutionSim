import java.awt.*;

public class Animal extends Circle{

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

    private int health;
    private int hunger;
    private double velocityX;
    private double velocityY;


    public Animal (int positionX, int positionY, int sizeX, int sizeY, Color color, int health, int hunger, double speed) {
        super(positionX, positionY, sizeX, sizeY, color);
        this.health = health;
        this.hunger = hunger;
        this.velocityX = speed;
        this.velocityY = speed;
    }

    public int getHealth() {
        return health;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }


    public void spreadOut(Animal other) {
        int thisOtherX = this.getPositionX() - other.getPositionX();
        int otherThisX = other.getPositionX() - this.getPositionX();
        int thisOtherY = this.getPositionY() - other.getPositionY();
        int otherThisY = other.getPositionY() - this.getPositionY();

        if(thisOtherX == 1) {
            this.setPositionX(this.getPositionX()+2);
        }
        if(otherThisX == 1) {
            this.setPositionX(this.getPositionX()-2);
        }
        if(thisOtherY == 1) {
            this.setPositionY(this.getPositionY()+2);
        }
        if(otherThisY == 1) {
            this.setPositionY(this.getPositionY()-2);
        }
    }
}
