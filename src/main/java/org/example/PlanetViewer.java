import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class PlanetViewer extends JPanel {

    private PlanetGenerator generator = new PlanetGenerator();
    private planet currentPlanet;
    private long currentSeed;

    public PlanetViewer() {
        currentPlanet = generator.generatePlanet();
        currentSeed = System.currentTimeMillis();
    }

    public void generateNewPlanet() {
        currentPlanet = generator.generatePlanet();
        currentSeed = System.currentTimeMillis();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        Random random = new Random();

        planet p = currentPlanet;

        // SPACE BACKGROUND
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());

        // STARS
        g.setColor(Color.WHITE);

        for (int i = 0; i < 250; i++) {

            int starX = random.nextInt(getWidth());
            int starY = random.nextInt(getHeight());

            int starSize = random.nextInt(3) + 1;

            g.fillOval(starX, starY, starSize, starSize);
        }
        boolean hasSun = random.nextInt(100) < 50;

        if (hasSun) {

            int sunSize = 80 + random.nextInt(120);

            int sunX = random.nextInt(120);
            int sunY = random.nextInt(120);

            g.setColor(new Color(255,220,100,60));

            g.fillOval(
                    sunX - 30,
                    sunY - 30,
                    sunSize + 60,
                    sunSize + 60
            );

            g.setColor(new Color(255,210,50));

            g.fillOval(
                    sunX,
                    sunY,
                    sunSize,
                    sunSize
            );

            for (int i = 0; i < 8; i++) {

                g.setColor(
                        new Color(
                                random.nextInt(255),
                                random.nextInt(120),
                                255,
                                20
                        )
                );

                g.fillOval(
                        random.nextInt(getWidth()),
                        random.nextInt(getHeight()),
                        300,
                        180
                );
            }
        }

        // PLANET SIZE
        int size = Math.min(320, p.radius / 30);

        int x = getWidth() / 2 - size / 2;
        int y = getHeight() / 2 - size / 2;

        // PLANET COLOR
        Color planetColor;
        Color glowColor;

        switch (p.type) {

            case "Earth World":
                planetColor = new Color(40, 120, 255);
                glowColor = new Color(100, 150, 255, 90);
                break;

            case "Lava World":
                planetColor = new Color(255, 80, 0);
                glowColor = new Color(255, 120, 0, 90);
                break;

            case "Ice World":
                planetColor = new Color(150, 220, 255);
                glowColor = new Color(150, 255, 255, 90);
                break;

            case "Desert World":
                planetColor = new Color(210, 180, 80);
                glowColor = new Color(255, 220, 120, 90);
                break;

            default:
                planetColor = new Color(50, 220, 50);
                glowColor = new Color(50, 255, 50, 90);
        }
        boolean hasRings = random.nextInt(100) < 35;

        // ATMOSPHERE GLOW
        g.setColor(glowColor);
        g.fillOval(x - 18, y - 18, size + 36, size + 36);

        // RINGS FOR LARGE PLANETS
        if (hasRings) {

            g2.setColor(new Color(220, 220, 220, 120));

            g2.setStroke(new BasicStroke(4));

            g2.drawOval(
                    x - 45,
                    y + size / 3,
                    size + 90,
                    size / 3
            );
        }

        // PLANET GRADIENT
        GradientPaint gradient = new GradientPaint(
                x,
                y,
                Color.WHITE,
                x + size,
                y + size,
                planetColor
        );

        g2.setPaint(gradient);
        g2.fillOval(x, y, size, size);

        // SURFACE FEATURES

        if (p.type.equals("Earth World")) {

            g.setColor(new Color(34, 139, 34));

            for (int i = 0; i < 8; i++) {

                g.fillOval(
                        x + random.nextInt(size - 40),
                        y + random.nextInt(size - 40),
                        random.nextInt(40) + 20,
                        random.nextInt(30) + 15
                );
            }

        } else if (p.type.equals("Lava World")) {

            g.setColor(Color.YELLOW);

            for (int i = 0; i < 12; i++) {

                g.fillOval(
                        x + random.nextInt(size),
                        y + random.nextInt(size),
                        random.nextInt(20) + 8,
                        random.nextInt(20) + 8
                );
            }

        } else if (p.type.equals("Ice World")) {

            g.setColor(new Color(240, 240, 255));

            for (int i = 0; i < 12; i++) {

                g.fillOval(
                        x + random.nextInt(size),
                        y + random.nextInt(size),
                        random.nextInt(25) + 10,
                        random.nextInt(25) + 10
                );
            }

        } else if (p.type.equals("Desert World")) {

            g.setColor(new Color(139, 69, 19));

            for (int i = 0; i < 12; i++) {

                g.fillOval(
                        x + random.nextInt(size),
                        y + random.nextInt(size),
                        random.nextInt(20) + 10,
                        random.nextInt(20) + 10
                );
            }

        } else {

            g.setColor(new Color(0, 255, 0, 120));

            for (int i = 0; i < 15; i++) {

                g.fillOval(
                        x + random.nextInt(size),
                        y + random.nextInt(size),
                        random.nextInt(30) + 15,
                        random.nextInt(30) + 15
                );
            }
        }

        // MOONS
        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < p.moons; i++) {

            double angle =
                    Math.toRadians(
                            random.nextInt(360)
                    );

            int orbitRadius =
                    size / 2 +
                            70 +
                            random.nextInt(60);

            int moonSize =
                    12 +
                            random.nextInt(18);

            int moonX =
                    x + size / 2 +
                            (int)(Math.cos(angle) * orbitRadius);

            int moonY =
                    y + size / 2 +
                            (int)(Math.sin(angle) * orbitRadius);

            g.setColor(Color.LIGHT_GRAY);

            g.fillOval(
                    moonX,
                    moonY,
                    moonSize,
                    moonSize
            );
        }

// PLANET BORDER
        g.setColor(Color.WHITE);
        g.drawOval(x, y, size, size);

        // HUD PANEL
        g.setColor(new Color(20, 20, 30, 220));
        g.fillRoundRect(15, 15, 320, 520, 20, 20);

        g.setColor(Color.CYAN);
        g.drawRoundRect(15, 15, 320, 520, 20, 20);

        g.setFont(new Font("Consolas", Font.BOLD, 18));

        g.drawString("PLANET SCANNER", 65, 40);

        int statsX = 30;
        int statsY = 80;

        g.setColor(Color.WHITE);

        g.drawString("Name: " + p.name, statsX, statsY);
        statsY += 30;

        g.drawString("Type: " + p.type, statsX, statsY);
        statsY += 30;

        g.drawString("Radius: " + p.radius + " km", statsX, statsY);
        statsY += 30;

        g.drawString(
                String.format("Gravity: %.2f G", p.gravity),
                statsX,
                statsY
        );
        statsY += 30;

        g.drawString(
                "Temp: " + p.temperature + " C",
                statsX,
                statsY
        );
        statsY += 30;

        g.drawString(
                "Water: " + p.waterCoverage + "%",
                statsX,
                statsY
        );
        statsY += 30;

        g.drawString(
                "Atmosphere:",
                statsX,
                statsY
        );
        statsY += 25;

        g.drawString(
                p.atmosphere,
                statsX,
                statsY
        );
        statsY += 35;

        g.drawString(
                "Moons: " + p.moons,
                statsX,
                statsY
        );
        statsY += 30;

        if (p.habitabilityScore >= 80)
            g.setColor(Color.GREEN);
        else if (p.habitabilityScore >= 40)
            g.setColor(Color.YELLOW);
        else
            g.setColor(Color.RED);

        g.drawString(
                "Habitability: " + p.habitabilityScore + "%",
                statsX,
                statsY
        );

        statsY += 30;

        g.setColor(Color.WHITE);

        g.drawString(
                "Existence: " + p.existenceLikelihood,
                statsX,
                statsY
        );

        statsY += 30;

        String life =
                p.habitabilityScore >= 70 ? "YES" : "NO";

        g.setColor(
                life.equals("YES")
                        ? Color.GREEN
                        : Color.RED
        );

        g.drawString(
                "Life Detected: " + life,
                statsX,
                statsY
        );

        statsY += 30;

        g.setColor(Color.CYAN);

        g.drawString(
                "Seed: " + currentSeed,
                statsX,
                statsY
        );
    }
}