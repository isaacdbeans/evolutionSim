import javax.swing.*;
import java.awt.*;

public class EvolutionSim {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Moving Shapes Example");

        NatureFrame panel = new NatureFrame();
        frame.add(panel);
        frame.setBackground(new Color(200, 200, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }
}
