package javastart;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DatabaseSource extends JPanel {

    private JLabel database;
    private JButton select, create, repair, compact;


    public DatabaseSource() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder("Database"), new EmptyBorder(12, 0, 0, 0)));
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JPanel jPanel = new JPanel(new GridBagLayout());
        jPanel.add(new Label("Database :"),gridBagConstraints);



        add((select=new JButton("Select")),gridBagConstraints);
        gridBagConstraints.gridx++;
        add((create=new JButton("Create")),gridBagConstraints);
        gridBagConstraints.gridx++;
        add(( repair=new JButton("Repair")),gridBagConstraints);
        gridBagConstraints.gridx++;
        add((compact= new JButton("Compact")),gridBagConstraints);



    }


    public JLabel getDatabase() {
        return database;
    }

    public void setDatabase(JLabel database) {
        this.database = database;
    }

    public JButton getSelect() {
        return select;
    }

    public void setSelect(JButton select) {
        this.select = select;
    }

    public JButton getCreate() {
        return create;
    }

    public void setCreate(JButton create) {
        this.create = create;
    }

    public JButton getRepair() {
        return repair;
    }

    public void setRepair(JButton repair) {
        this.repair = repair;
    }

    public JButton getCompact() {
        return compact;
    }

    public void setCompact(JButton compact) {
        this.compact = compact;
    }
}
