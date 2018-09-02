package javastart;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {


    public MyFrame() {

        super("Panel sterowania");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());


        jPanel.add(new Label("Etykieta"));
        jPanel.add(new Button("Zapraszamy"));
        jPanel.add(new TextField("Wpisz tekst"));





        getContentPane().add(jPanel);


        pack();

        setVisible(true);


    }


}
