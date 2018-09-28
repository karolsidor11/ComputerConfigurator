package panels;

import daoimpl.CustomerJPA;
import model.Adres;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCustomerPanel extends JDialog {

    Object[] row = new Object[4];
    private JLabel insertName, insertLastName, insertAdress, insertStreet, insertNumber, insertZipCode;
    private JTextField name, lastName, adres, zipCode, number, street;
    private JButton confirm;
    private JDialog jDialog;
    private GridBagConstraints gbc;


    public AddCustomerPanel() {

        createFrame();
        createComponent();
        addComponents();

    }

    public void createActionListner(JButton jButton, DefaultTableModel tableModel) {

        String[] columnNames = {"id", "Imię", "Nazwisko", "Adres"};
        jButton= new JButton();
        tableModel= new DefaultTableModel(columnNames,0);

        DefaultTableModel finalTableModel = tableModel;
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Witaj w ADD APANEL");

                CustomerJPA customerJPA = new CustomerJPA();
                Adres adress = new Adres();
                Customer customer = new Customer();

                try {
                    customer.setName(name.getText());
                    customer.setLastname(lastName.getText());
                    adress.setLocality(adres.getText());
                    adress.setStreet(street.getText());
                    adress.setStreetNumber(Integer.parseInt(number.getText()));
                    adress.setZipCode(zipCode.getText());
                    customer.setAdres(adress);
                    customerJPA.addCustomer(customer);

                    row[0] = customer.getId();
                    row[1] = name.getText();
                    row[2] = lastName.getText();
                    row[3] = adres.getText();
                    finalTableModel.addRow(row);
                    jDialog.dispose();

                } catch (Exception a) {
                    JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszytskie dane !!!");
                }
            }
        });

    }

    public void addComponents() {
        jDialog.add(insertName, gbc);
        gbc.gridx++;
        jDialog.add(name, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertLastName, gbc);
        gbc.gridx++;
        jDialog.add(lastName, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertAdress, gbc);
        gbc.gridx++;
        jDialog.add(adres, gbc);

        gbc.gridy++;
        gbc.gridx = 0;


        jDialog.add(insertStreet, gbc);
        gbc.gridx++;
        jDialog.add(street, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertNumber, gbc);
        gbc.gridx++;
        jDialog.add(number, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertZipCode, gbc);
        gbc.gridx++;
        jDialog.add(zipCode, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.weighty++;
        gbc.insets = new Insets(15, 0, 15, 5);

        jDialog.add(confirm, gbc);

        jDialog.setVisible(true);
        jDialog.pack();

    }

    public void createFrame() {
        jDialog = new JDialog();
        jDialog.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        jDialog.setSize(280, 280);
        jDialog.setTitle("Panel dodawania klienta");
        jDialog.setLocationRelativeTo(null);
        jDialog.setResizable(false);
    }


    public void createComponent() {
        insertName = new JLabel("Wprowadź imię :");
        insertLastName = new JLabel("Wprowadź nazwisko :");
        insertAdress = new JLabel("Wprowadź miejscowość : ");
        insertStreet = new JLabel("Wprowadź ulicę :");
        insertNumber = new JLabel("Wprowadź  numer :");
        insertZipCode = new JLabel("Wprowadź kod pocztowy :");

        name = new JTextField();
        lastName = new JTextField();
        adres = new JTextField();
        zipCode = new JTextField();
        number = new JTextField();
        street = new JTextField();
        confirm = new JButton("Zatwierdź");


        name.setColumns(12);
        lastName.setColumns(12);
        adres.setColumns(12);
        number.setColumns(12);
        zipCode.setColumns(12);
        street.setColumns(12);
    }
}

