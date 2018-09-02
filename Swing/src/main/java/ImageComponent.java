import javax.swing.*;
import java.awt.*;

public class ImageComponent extends JComponent {

    public void paint(Graphics g) {

        Image image = new ImageIcon("logo.jpg").getImage();
        g.drawImage(image, 100, 100, 200,200,null);




    }
}
