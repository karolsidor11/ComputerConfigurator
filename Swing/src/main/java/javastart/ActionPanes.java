package javastart;

import javax.swing.*;
import java.awt.*;

public class ActionPanes extends JFrame {
    private JButton  Ok, Cancel , Help, Advanced , Option;


     public ActionPanes(){
         setLayout(new GridBagLayout());
         GridBagConstraints gbc = new GridBagConstraints();
         gbc.gridx = 0;
         gbc.gridy = 0;
         gbc.gridwidth = GridBagConstraints.REMAINDER;
         gbc.fill = GridBagConstraints.HORIZONTAL;
         gbc.weightx = 1;
         gbc.insets = new Insets(4, 4, 4, 4);
         add((Ok = new JButton("Ok")), gbc);
         gbc.gridy++;
         add((Cancel = new JButton("Cancel")), gbc);
         gbc.gridy++;
         add((Help = new JButton("Help")), gbc);
         gbc.gridy++;
         add((Advanced = new JButton("Advanced")), gbc);
         gbc.gridy++;
         gbc.weighty = 1;
         gbc.anchor = GridBagConstraints.SOUTH;
         add((Option = new JButton("Options >>")), gbc);





     }

    public JButton getOk() {
        return Ok;
    }

    public JButton getCancel() {
        return Cancel;
    }

    public JButton getHelp() {
        return Help;
    }

    public JButton getAdvanced() {
        return Advanced;
    }

    public JButton getOption() {
        return Option;
    }
}
