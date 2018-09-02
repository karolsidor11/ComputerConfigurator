package javastart;

import javax.swing.*;
import java.awt.*;

public class SourcePanes extends JPanel {
    private JTextField dataSourceName;
    private JTextField description;

    public SourcePanes() {
        setLayout(new GridBagLayout());
        GridBagConstraints gird = new GridBagConstraints();
        gird.gridx = 0;
        gird.gridy = 0;
        gird.anchor = GridBagConstraints.WEST;
        add(new JLabel("Test source name"), gird);
        gird.gridy++;
        add(new JLabel("Description"), gird);
        gird.gridx++;
        gird.gridy = 0;
        gird.weightx = 1;
        gird.fill = GridBagConstraints.HORIZONTAL;
        add((dataSourceName = new JTextField(10)), gird);
        gird.gridy++;
        add((description = new JTextField(10)), gird);


    }

    public JTextField getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(JTextField dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public JTextField getDescription() {
        return description;
    }

    public void setDescription(JTextField description) {
        this.description = description;
    }
}
