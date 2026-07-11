import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PlanetViewer extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Random random = new Random();

        int size = random.nextInt(151) + 100;

        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;

        Color planetColor = new Color(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );

        g.setColor(planetColor);

        g.fillOval(x, y, size, size);

        g.setColor(Color.WHITE);

        for (int i = 0; i < 15; i++) {

            int craterX = x + random.nextInt(size);
            int craterY = y + random.nextInt(size);

            int craterSize = random.nextInt(15) + 5;

            g.drawOval(craterX, craterY, craterSize, craterSize);
        }
    }
}