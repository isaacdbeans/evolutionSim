import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class NatureFrame extends JPanel implements ActionListener {

    private JPanel mainPanel;
    private ArrayList<Herbivore> herbs = new ArrayList<>();
    private ArrayList<Carnivore> carns = new ArrayList<>();
    private ArrayList<Food> food = new ArrayList<>();
    private Random rng;
    private int decayCounter;

    public NatureFrame() {
        this.mainPanel = new JPanel();
        this.mainPanel.setDoubleBuffered(true);
        this.mainPanel.setBackground(new Color(200, 200, 200));
        this.rng = new Random();
        this.decayCounter = 0;

        for (int i = 0; i < 100; i++) {
            if(rng.nextBoolean()) {
                herbs.add(new Herbivore(
                        rng.nextInt(10, 790),
                        rng.nextInt(10, 790),
                        15, 15,
                        new Color(0, 255, 0),
                        10, 0, 1));
            } else {
                carns.add(new Carnivore(
                        rng.nextInt(10, 790),
                        rng.nextInt(10, 790),
                        15, 15,
                        new Color(255, 0, 0),
                        10, 10, 1));
            }
        }
        for (int i = 0; i < 30; i++) {
            food.add(new Food(rng.nextInt(100, 700), rng.nextInt(100, 700)
                                            , 5, 5, new Color(0, 0, 255)));
        }
        Timer timer = new Timer(32, this);
        timer.setRepeats(true);
        timer.start();
    }

    private void particlePull(double g, ArrayList<? extends Circle> list1, ArrayList<? extends Circle> list2) {
        for (Circle animal1 : list1) {
            double fx = 0;
            double fy = 0;
            for (Circle animal2 : list2) {
                int dx = animal1.getPositionX() - animal2.getPositionX();
                int dy = animal1.getPositionY() - animal2.getPositionY();
                double d = Math.sqrt(dx*dx + dy*dy);
                if(d > 0 && d < 120) {
                    double F = g/d;
                    fx += (F*dx);
                    fy += (F*dy);
                }
            }
            animal1.setVelocityX(0.5 * (animal1.getVelocityX() + fx));
            animal1.setVelocityY(0.5 * (animal1.getVelocityY() + fy));
            animal1.move();

            if (animal1.getPositionX() < 70 || animal1.getPositionX() > 730) {
                animal1.setVelocityX(animal1.getVelocityX() * -1);
            }
            if (animal1.getPositionY() < 100 || animal1.getPositionY() > 750) {
                animal1.setVelocityY(animal1.getVelocityY() * -1);
            }
            repaint();
        }
    }

    private void foodRespawn(Herbivore herb) {
        for (Food value : food) {
            if (herb.getPositionX() - value.getPositionX() > -5 && herb.getPositionX() - value.getPositionX() < 5) {
                if (herb.getPositionY() - value.getPositionY() > -5 && herb.getPositionY() - value.getPositionY() < 5) {
                    value.setPositionX(rng.nextInt(100, 700));
                    value.setPositionY(rng.nextInt(100, 700));
                    herb.setHunger(herb.getHunger() - 3);
                    if (herb.getHunger() < 0) {
                        herb.setHunger(0);
                    }
                }
            }
            repaint();
        }
    }

    private void decay() {
        for (int i = 0; i < herbs.size(); i++) {
            herbs.get(i).setHunger(herbs.get(i).getHunger() + 1);
            if (herbs.get(i).getHunger() > 10) {
                herbs.remove(i);
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Herbivore herb : herbs) {
            g.setColor(new Color(0, 255 - (herb.getHunger()*10), 0));
            g.fillOval(herb.getPositionX(), herb.getPositionY(), herb.getSizeX(), herb.getSizeY());
        }

        for (Carnivore carn : carns) {
            g.setColor(new Color(255, 0, 0));
            g.fillOval(carn.getPositionX(), carn.getPositionY(), carn.getSizeX(), carn.getSizeY());
        }

        for (Food food1 : food) {
            g.setColor(new Color(0, 0, 255));
            g.fillOval(food1.getPositionX(), food1.getPositionY(), food1.getSizeX(), food1.getSizeY());
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        particlePull(-1, herbs, food);
        particlePull(1, carns, carns);
        particlePull(-1, carns, herbs);
        particlePull(1, herbs, carns);

        this.decayCounter++;
        for (Herbivore herb : herbs) {
            foodRespawn(herb);
        }
        if (decayCounter > 30) {
            decay();
            decayCounter = 0;
        }
    }
}
