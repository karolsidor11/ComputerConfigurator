package javastart;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Component extends JComponent {


    public Component() {


    }

    public void paintComponent(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;

        Ellipse2D ellipse2D = new Ellipse2D.Float();
        ellipse2D.setFrame(40, 40, 40, 40);

        graphics2D.draw(ellipse2D);
    }
}
