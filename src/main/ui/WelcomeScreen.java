package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

//Represents the title screen image when the application is first launched
public class WelcomeScreen extends JPanel {

    private Image backgroundImage;

    //EFFECTS: loads the background image from file
    public WelcomeScreen() throws IOException {
        backgroundImage = ImageIO.read(new File("./data/Lab Equipment Manager.png"));
    }

    //MODIFIES: this
    //EFFECTS: draws the background image onto the panel
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this);
    }
}


