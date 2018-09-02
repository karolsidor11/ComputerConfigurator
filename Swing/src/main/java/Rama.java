import javax.swing.*;
import java.awt.*;

public class Rama {
    public static void main(String[] args) {

        JFrame jFrame = new JFrame();
        jFrame.setTitle(" Nowa ramka");
        jFrame.toFront();

        jFrame.setResizable(false);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dimension = kit.getScreenSize();
        int weight = dimension.width;
        int heigt = dimension.height;

        jFrame.setSize(weight/2, heigt/2);

        jFrame.setLocation(200, 200);
        Image img = new ImageIcon("logo.jpg").getImage();

        jFrame.setIconImage(img);
        jFrame.setVisible(true);

    }
}
