package javastart;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                JFrame jFrame = new JFrame();
                jFrame.setTitle("Moja aplikacja");
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jFrame.add(new Database());

                jFrame.pack();
                jFrame.setVisible(true);

            }
        });

    }

    public static class Database extends JPanel {
        private SourcePanes sourcePane;
        private DatabaseSource databaseSource;
        private SystemPane systemPane;
        private ActionPanes actionPanes;

        public Database() {

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
          //  gbc.gridx = 0;
          //  gbc.gridy = 0;
         //   gbc.weightx = 1;
         //   gbc.weighty = 0.33;
      //      gbc.anchor = GridBagConstraints.WEST;
       //     gbc.fill = GridBagConstraints.BOTH;
        //    gbc.insets = new Insets(4, 4, 4, 4);
            add((sourcePane = new SourcePanes()), gbc);
         //   gbc.gridy++;
            add((databaseSource= new DatabaseSource()), gbc);
          //  gbc.gridy++;
            add((systemPane = new SystemPane()), gbc);
         //   gbc.gridy = 0;
        //    gbc.gridx++;
            gbc.gridheight = GridBagConstraints.REMAINDER;
          //  gbc.fill = GridBagConstraints.VERTICAL;
         //   gbc.weighty = 1;
        //    gbc.weightx = 0;
         //   add((actionPanes = new ActionPanes()), gbc);

        }
    }
}
