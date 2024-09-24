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
    private Random rng;

    public NatureFrame() {
        this.mainPanel = new JPanel();
        this.mainPanel.setDoubleBuffered(true);
        this.mainPanel.setBackground(new Color(200, 200, 200));
        this.rng = new Random();

        for (int i = 0; i < 100; i++) {
            if(rng.nextBoolean()) {
                herbs.add(new Herbivore(
                        rng.nextInt(10, 790),
                        rng.nextInt(10, 790),
                        15, 15,
                        new Color(0, 255, 0),
                        10, 10, 1));
            } else {
                carns.add(new Carnivore(
                        rng.nextInt(10, 790),
                        rng.nextInt(10, 790),
                        15, 15,
                        new Color(255, 0, 0),
                        10, 10, 1));
            }
        }
        Timer timer = new Timer(16, this);
        timer.setRepeats(true);
        timer.start();
    }

    private void particlePull(double g, ArrayList<? extends Animal> list1, ArrayList<? extends Animal> list2) {
        for (Animal animal1 : list1) {
            double fx = 0;
            double fy = 0;
            for (Animal animal2 : list2) {
                animal1.spreadOut(animal2);
                int dx = animal1.getPositionX() - animal2.getPositionX();
                int dy = animal1.getPositionY() - animal2.getPositionY();
                double d = Math.sqrt(dx*dx + dy*dy);
                if(d > 0 && d < 80) {
                    double F = g/d;
                    fx += (F*dx);
                    fy += (F*dy);
                }
            }
            animal1.setVelocityX(0.5 * animal1.getVelocityX() + fx);
            animal1.setVelocityY(0.5 * animal1.getVelocityY() + fy);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Herbivore herb : herbs) {
            g.setColor(new Color(0, 255, 0));
            g.fillOval(herb.getPositionX(), herb.getPositionY(), herb.getSizeX(), herb.getSizeY());
        }

        for (Carnivore carn : carns) {
            g.setColor(new Color(255, 0, 0));
            g.fillOval(carn.getPositionX(), carn.getPositionY(), carn.getSizeX(), carn.getSizeY());
        }
    }

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        particlePull(-0.001, herbs, herbs);
        particlePull(0.001, carns, carns);



    }
}
