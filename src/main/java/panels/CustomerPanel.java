package panels;

import daoimpl.CustomerJPA;
import frame.MainFrame;
import model.Adres;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomerPanel extends JPanel {

    private JButton addButton;
    private JButton deleteButton;
    private JButton update;

    private JButton back;
    private JTable tableCustomers;
    private JScrollPane scrollBar;
    private JToolBar toolBarButton;

    private JLabel insertName, insertLastName, insertAdress, insertZipCode, insertStreet, insertNumber;
    private JTextField name, lastName, adres, zipCode, street, number;
    private JButton confirm = new JButton();
    private DefaultTableModel modelCustomer;
    private Font font;

    public CustomerPanel() {
        createComponent();
        createTable();
        createToolBarButton();
        createActionListner();
    }

    private void createComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
    }

    private void createToolBarButton() {
        addButton = new JButton("Dodaj ");
        deleteButton = new JButton("Usuń ");
        back = new JButton("Wstecz");
        update = new JButton("Modyfikuj");
        font = new Font(Font.DIALOG, Font.PLAIN, 12);
        addButton.setFont(font);
        deleteButton.setFont(font);
        back.setFont(font);
        update.setFont(font);
        toolBarButton = new JToolBar();
        toolBarButton.add(back);
        toolBarButton.add(addButton);
        toolBarButton.add(update);
        toolBarButton.add(deleteButton);
        this.add(toolBarButton);
    }

    private void createTable() {

        CustomerJPA customerJPA = new CustomerJPA();
        List<Customer> customers = customerJPA.allCustomer();

        String[] columnNames = {"id", "Imię", "Nazwisko", "Adres"};
        modelCustomer = new DefaultTableModel(columnNames, 0);

        for (int i = 0; i < customers.size(); i++) {
            Integer id = customers.get(i).getId();
            String name = customers.get(i).getName();
            String lastName = customers.get(i).getLastname();
            String adress = customers.get(i).getAdres().getLocality();

            Object[] objects = {id, name, lastName, adress};

            modelCustomer.addRow(objects);
        }

        tableCustomers = new JTable(modelCustomer);
        tableCustomers.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        tableCustomers.setBackground(Color.YELLOW);
        tableCustomers.setPreferredScrollableViewportSize(new Dimension(400, 350));
        scrollBar = new JScrollPane(tableCustomers);
        this.add(scrollBar);
    }

    private void createActionListner() {
        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                        AddCustomerPanel addCustomerPanel = new AddCustomerPanel(tableCustomers, modelCustomer, "Add", "Panel dodawania klienta");

                    }
                }
        );
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (tableCustomers.isRowSelected(tableCustomers.getSelectedRow()) == true) {
                    AddCustomerPanel addCustomerPanel = new AddCustomerPanel(tableCustomers, modelCustomer, "Update", "Panel modyfikacji klienta");
                    addCustomerPanel.setCustomerValue();

                } else {
                    JOptionPane.showMessageDialog(null, "Wybierz klienta do modyfikacji !");
                }
            }
        });

        deleteButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createPanelDeleteCustomer();
                    }
                }
        );
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                backToMainFrame();
            }
        });

    }

    private void createPanelAddCustomer() {
        JDialog jDialog = new JDialog();

        jDialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0;
        gbc.insets = new Insets(4, 4, 4, 4);

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

        jDialog.setSize(280, 280);
        jDialog.setTitle("Panel dodawania klienta");
        jDialog.setLocationRelativeTo(null);
        jDialog.setResizable(false);

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

        Object[] row = new Object[4];

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
        });
    }

    private void createPanelUpdateCustomer() {

        JDialog jDialog = new JDialog();
        jDialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);


        JTextField ulica;
        JTextField code;
        JTextField numbers;

        insertName = new JLabel("Wprowadź imię :");
        insertLastName = new JLabel("Wprowadź nazwisko :");
        insertAdress = new JLabel("Wprowadź miejscowość : ");
        insertStreet = new JLabel("Wprowadź ulicę :");
        insertNumber = new JLabel("Wprowadź  numer :");
        insertZipCode = new JLabel("Wprowadź kod pocztowy :");


        name = new JTextField();
        lastName = new JTextField();
        adres = new JTextField();
        ulica = new JTextField();
        numbers = new JTextField();
        code = new JTextField();
        confirm = new JButton("Zatwierdź");

        name.setColumns(12);
        lastName.setColumns(12);
        adres.setColumns(12);
        ulica.setColumns(12);
        numbers.setColumns(12);
        code.setColumns(12);

        //   jDialog.setLayout(new FlowLayout());
        jDialog.setSize(280, 280);
        jDialog.setTitle("Panel modyfikacji klienta");
        jDialog.setLocationRelativeTo(null);
        jDialog.setResizable(false);


        int a = tableCustomers.getSelectedRow();

        String valueAt = tableCustomers.getModel().getValueAt(a, 0).toString();


        name.setText((String) modelCustomer.getValueAt(a, 1));
        lastName.setText((String) modelCustomer.getValueAt(a, 2));
        adres.setText((String) modelCustomer.getValueAt(a, 3));

        CustomerJPA customerJPA = new CustomerJPA();
        List<Customer> customers = customerJPA.allCustomer();
        Integer valueAt1 = (Integer) tableCustomers.getModel().getValueAt(a, 0);

        for (Customer b : customers) {
            Adres adres = b.getAdres();
            if (b.getId().equals(valueAt1)) {
                ulica.setText(adres.getStreet());
                numbers.setText((String.valueOf(adres.getStreetNumber())));
                code.setText(adres.getZipCode());
            }
        }

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
        jDialog.add(ulica, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertNumber, gbc);
        gbc.gridx++;
        jDialog.add(numbers, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertZipCode, gbc);
        gbc.gridx++;
        jDialog.add(code, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.insets = new Insets(15, 0, 15, 5);


        jDialog.add(confirm, gbc);
        jDialog.setVisible(true);
        jDialog.pack();


        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerJPA customerJPA1 = new CustomerJPA();
                Customer customer = new Customer();
                Adres adress = new Adres();

                try {
                    int selectedRow = tableCustomers.getSelectedRow();
                    String valueAt1 = tableCustomers.getModel().getValueAt(selectedRow, 0).toString();
                    Customer byId = customerJPA1.getById(Integer.parseInt(valueAt1));

                    byId.setName(name.getText());
                    byId.setLastname(lastName.getText());

                    adress.setLocality(adres.getText());
                    adress.setStreet(ulica.getText());
                    adress.setZipCode(code.getText());
                    adress.setStreetNumber(Integer.parseInt(numbers.getText()));
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


                } catch (Exception a) {
                    JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!!");

                }
            }
        });
    }

    private void createPanelDeleteCustomer() {
        Integer i = tableCustomers.getSelectedRow();
        if (i >= 0) {

            String valueAt = tableCustomers.getModel().getValueAt(i, 0).toString();
            CustomerJPA customerJPA = new CustomerJPA();
            customerJPA.removeById(Integer.parseInt(valueAt));
            modelCustomer.removeRow(i);

        } else {
            JOptionPane.showMessageDialog(this, "Wybierz klienta do usunięcia !");
        }

    }

    private void backToMainFrame() {
        try {
            MainFrame mainFrame = new MainFrame();
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }
}

