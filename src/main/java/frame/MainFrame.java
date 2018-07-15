package frame;

import panels.CustomerPanel;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton customer;
    private JButton component;
    private JButton computerSet;
    private JPanel panel;


    public MainFrame() {
        customer = new JButton("Klienci");
        component = new JButton("Podzespoły");
        computerSet = new JButton("Zamówienia");

        panel = new JPanel();
        panel.add(customer);
        panel.add(component);
        panel.add(computerSet);

        add(panel);
        setSize(500, 500);
        setTitle("Nowa Ramka");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actionListner();

    }
    public void actionListner(){
        customer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane( new CustomerPanel());
                pack();

               // getContentPane().add(new CustomerPanel());


            }
        });

    }
}
