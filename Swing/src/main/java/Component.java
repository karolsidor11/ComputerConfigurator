import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;


public class Component extends JComponent {


    public void paintComponent(Graphics g) {

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setPaint(Color.red);
        // Rysowanie prostokata

        Rectangle2D rectangle2D = new Rectangle2D.Float();
        rectangle2D.setFrame(100, 100, 200, 150);
        graphics2D.fill(rectangle2D);

        //Rysowanie Okregu


        // Rysowanie lini

        Line2D line2D = new Line2D.Double();
        line2D.setLine(100, 100, 300, 250);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.draw(line2D);

        // Okrąg opinany

        Ellipse2D ellipse2D = new Ellipse2D.Float();
        ellipse2D.setFrame(100, 100, 200, 150);

        graphics2D.setPaint(SystemColor.yellow);
        graphics2D.fill(ellipse2D);
        graphics2D.setBackground(Color.gray);

        // Napis
        graphics2D.setColor(Color.BLACK);
        Font font = new Font("Tiger", Font.BOLD, 14);
        graphics2D.setFont(font);

        graphics2D.drawString("Witaj w świecie SWINGA", 100, 50);


        // Napis pod

        graphics2D.setColor(Color.BLUE);
        graphics2D.setFont(new Font("Arial", Font.ITALIC, 24));

        graphics2D.drawString("Rys.1  Pole koła ", 150, 280);

        // Wyświetlanie obrazów

        Image image = new ImageIcon("logo.jpg").getImage();

        graphics2D.drawImage(image,200, 300 ,null);
    }


}
