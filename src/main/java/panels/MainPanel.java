package panels;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private JButton button;
    private JTextField text, jTextField;
    private JPanel panelCustomer;


    public MainPanel() {
        createComponent();
        createActionListner();
    }

    private void createComponent() {
        text = new JTextField("Pole tekstowe");
        button = new JButton("Przycisk");
        jTextField = new JTextField("Pole");
        button.setLocation(150, 150);
        add(text);
        add(button);
        add(jTextField);
    }

    private void createActionListner() {
        button.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                    }
                }
        );
    }

}
