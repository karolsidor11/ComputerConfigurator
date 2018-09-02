import com.sun.javafx.event.EventQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main  implements ActionListener{
    public static void main(String[] args) {


        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame jFrame = new JFrame("APka");
                jFrame.setSize(500, 500);
                jFrame.setLayout(null);
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


                JButton j = new JButton(" Start");


                j.setLocation(200,400);
                j.setSize(240, 150);
                jFrame.add(j);
               // jFrame.pack();
                jFrame.setVisible(true);

            }
        });


    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}



