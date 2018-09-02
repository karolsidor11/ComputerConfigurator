import javax.swing.*;

public class ButtonPanel extends JPanel {

    public final int weight = 100;
    public final int height = 300;
    JButton czerwony;
    JButton zielony;
    JButton niebieski;


    public ButtonPanel() {

        czerwony= new JButton("Czerwony");
        zielony= new JButton("Zielony");
        niebieski= new JButton("Niebieski");


    }
}
