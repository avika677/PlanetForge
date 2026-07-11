import javax.swing.*;

public class PlanetWindow {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PlanetForge");

        frame.setSize(500, 500);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new PlanetViewer());

        frame.setVisible(true);
    }
}