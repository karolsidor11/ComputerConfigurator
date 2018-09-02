import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {


    public Panel() {

        setLocation(200, 200);
        setSize(400, 400);
        Button button = new Button();
        button.setLabel("Zatwierdź");
        button.setBackground(SystemColor.CYAN);
        button.setSize(100, 50);
        button.setLocation(150, 350);
        add(button);
        add(new ImageComponent());

    }
}
