import javax.swing.*;
import java.awt.*;

public class PlanetWindow {

    public static void main(String[] args) {

        JFrame frame = new JFrame("PlanetForge");

        frame.setSize(700, 700);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLayout(new BorderLayout());

        PlanetViewer viewer = new PlanetViewer();

        JButton generateButton = new JButton("Generate New Planet");

        generateButton.setBackground(new Color(34, 197, 94));
        generateButton.setForeground(Color.WHITE);

        generateButton.setFont(new Font("Arial", Font.BOLD, 18));

        generateButton.setFocusPainted(false);

        generateButton.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        generateButton.addActionListener(e -> {
            viewer.repaint();
        });

        frame.add(viewer, BorderLayout.CENTER);
        frame.add(generateButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}