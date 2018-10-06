package panels;

import daoimpl.ComputerComponentJPA;
import model.ComputerComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class AddComputerComponentPanel extends JDialog {

    private JTextField componentName;
    private JTextField componentDescription;
    private JTextField componentPrice;
    private JLabel insertComponentName;
    private JLabel insertComponentDescription;
    private JLabel insertPrice;
    private JButton confirm;
    private GridBagConstraints gbc;
    private String status;
    private String title;
    private DefaultTableModel model;
    private JTable tableComponent;


    public AddComputerComponentPanel(DefaultTableModel model, String status, JTable jTable, String title) {

        this.status = status;
        this.model = model;
        this.tableComponent = jTable;
        setTitle(title);
        createFrame();
        createComponents();
        addComponents();
        actionButton();
    }

    private void createFrame() {

        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    private void createComponents() {
        componentName = new JTextField();
        componentDescription = new JTextField();
        componentPrice = new JTextField();
        insertComponentName = new JLabel("Wprowadź nazwę produktu: ");
        insertComponentDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        confirm = new JButton("Zatwierdź");

        componentName.setColumns(15);
        componentDescription.setColumns(15);
        componentPrice.setColumns(15);
    }

    private void addComponents() {
        add(insertComponentName, gbc);
        gbc.gridx++;
        add(componentName, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        add(insertComponentDescription, gbc);
        gbc.gridx++;
        add(componentDescription, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        add(insertPrice, gbc);
        gbc.gridx++;
        add(componentPrice, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.weighty = 1;
        gbc.insets = new Insets(15, 0, 15, 5);
        gbc.anchor = GridBagConstraints.NORTH;

        add(confirm, gbc);
        setVisible(true);
        pack();
        setResizable(false);
    }

    private void actionButton() {
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (status.equals("Add")) {
                    addComputerComponent();
                }
                if (status.equals("Update")) {
                    modifyComupterComponent();
                }
            }
        });
    }

    private void addComputerComponent() {
        ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
        ComputerComponent computerComponent = new ComputerComponent();

        try {
            computerComponent.setComponentName(componentName.getText());
            computerComponent.setComponentDescribe(componentDescription.getText());
            computerComponent.setPrice(BigDecimal.valueOf(Integer.parseInt(componentPrice.getText())));

            computerComponentJPA.addComputerComponent(computerComponent);

            Object[] rows = new Object[4];
            rows[0] = computerComponent.getId();
            rows[1] = componentName.getText();
            rows[2] = componentDescription.getText();
            rows[3] = componentPrice.getText();

            model.addRow(rows);
            dispose();

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this, "Wprowadź poprawnie wszystkie dane !!!");
        }
    }

    private void modifyComupterComponent() {

        int i = tableComponent.getSelectedRow();

        ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
        String valueAt = tableComponent.getModel().getValueAt(i, 0).toString();

        ComputerComponent byId = computerComponentJPA.getById(Integer.parseInt(valueAt));

        try {
            byId.setComponentName(componentName.getText());
            byId.setComponentDescribe(componentDescription.getText());
            byId.setPrice(BigDecimal.valueOf(Integer.parseInt(componentPrice.getText())));


            if (!componentName.getText().equals("") && !componentDescription.getText().equals("")) {

                computerComponentJPA.mergeComponent(byId);

                model.setValueAt(byId.getId(), i, 0);
                model.setValueAt(componentName.getText(), i, 1);
                model.setValueAt(componentDescription.getText(), i, 2);
                model.setValueAt(componentPrice.getText(), i, 3);

                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Wprowadź poprawnie wszystkie dane !!! ");
            }


        } catch (Exception e1) {
            JOptionPane.showMessageDialog(this, "Wprowadź poprawnie wszystkie dane !!!");
        }
    }

    public void setComponentValue() {
        int a = tableComponent.getSelectedRow();
        componentName.setText((String) model.getValueAt(a, 1));
        componentDescription.setText((String) model.getValueAt(a, 2));
        componentPrice.setText(String.valueOf((BigDecimal) model.getValueAt(a, 3)));
    }
}
