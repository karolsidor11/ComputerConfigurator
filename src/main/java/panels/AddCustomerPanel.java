package panels;

import daoimpl.CustomerJPA;
import model.Adres;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddCustomerPanel extends JDialog {
    //TODO nazwy zmiennych do poprawy
    Object[] row = new Object[4];
    private JLabel insertName, insertLastName, insertAdress, insertStreet, insertNumber, insertZipCode;
    private JTextField name, lastName, adres, zipCode, number, street;
    private JButton confirm;
    private JDialog jDialog;
    private GridBagConstraints gbc;
    private JTable tableCustomers;
    private DefaultTableModel modelCustomer;
    private String status;


    public AddCustomerPanel(JTable tableCustomers, DefaultTableModel tableModel, String status, String title) {

        this.tableCustomers = tableCustomers;
        this.modelCustomer = tableModel;
        this.status = status;

        createFrame();
        createComponent();
        addComponents();
        actionButton();
        jDialog.setTitle(title);
    }

    private void createFrame() {
        jDialog = new JDialog();
        jDialog.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        jDialog.setSize(280, 280);
        jDialog.setLocationRelativeTo(null);
        jDialog.setResizable(false);
    }

    private void createComponent() {
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

    public void addComponents() {//TODO czy ta metody musi być publiczna ?
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

    private void actionButton() {

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (status.equals("Add")) {//TODO wydziel status do klasy ENUM.
                    createCustomer();
                }
                if (status.equals("Update")) {
                    setCustomerValue();
                    modifyCustomer();
                }
            }
        });
    }

    private void createCustomer() {
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
            modelCustomer.addRow(row);
            jDialog.dispose();

        } catch (Exception a) {
            JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszytskie dane !!!");
        }
    }

    private void modifyCustomer() {

        CustomerJPA customerJPA1 = new CustomerJPA();
        Adres adress = new Adres();

        try {
            int selectedRow = tableCustomers.getSelectedRow();
            String valueAt2 = tableCustomers.getModel().getValueAt(selectedRow, 0).toString();
            Customer byId = customerJPA1.getById(Integer.parseInt(valueAt2));

            byId.setName(name.getText());
            byId.setLastname(lastName.getText());

            adress.setLocality(adres.getText());
            adress.setStreet(street.getText());
            adress.setZipCode(zipCode.getText());
            adress.setStreetNumber(Integer.parseInt(number.getText()));
            byId.setAdres(adress);

            if (!byId.getName().equals("") && !byId.getLastname().equals("") &&
                    !adress.getLocality().equals("") && !adress.getStreet().equals("")
                    && !adress.getZipCode().equals("")) {

                customerJPA1.mergeCustomer(byId);
                int i = tableCustomers.getSelectedRow();
                modelCustomer.setValueAt(name.getText(), i, 1);
                modelCustomer.setValueAt(lastName.getText(), i, 2);
                modelCustomer.setValueAt(adres.getText(), i, 3);

                jDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!!");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!!");

        }
    }

    public void setCustomerValue() {//TODO czy ta metody musi być publiczna ?

        int a = tableCustomers.getSelectedRow();

        name.setText((String) modelCustomer.getValueAt(a, 1));
        lastName.setText((String) modelCustomer.getValueAt(a, 2));
        adres.setText((String) modelCustomer.getValueAt(a, 3));

        CustomerJPA customerJPA = new CustomerJPA();
        List<Customer> customers = customerJPA.allCustomer();
        Integer valueAt1 = (Integer) tableCustomers.getModel().getValueAt(a, 0);

        for (Customer b : customers) {
            Adres adres = b.getAdres();
            if (b.getId().equals(valueAt1)) {
                street.setText(adres.getStreet());
                number.setText((String.valueOf(adres.getStreetNumber())));
                zipCode.setText(adres.getZipCode());
            }
        }
    }
}


