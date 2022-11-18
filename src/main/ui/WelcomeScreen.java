package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen extends JPanel {

    private Image backgroundImage;

    public WelcomeScreen() throws IOException {
        backgroundImage = ImageIO.read(new File("./data/Lab Equipment Manager.png"));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(backgroundImage, 0, 0, this);
    }
}


