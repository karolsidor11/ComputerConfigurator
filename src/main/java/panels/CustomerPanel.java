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
    private JButton confirm;
    private DefaultTableModel modelCustomer;

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

        String[] columnNames = {"id", "imię", "nazwisko", "adres"};
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
        tableCustomers.setBackground(Color.YELLOW);
        tableCustomers.setPreferredScrollableViewportSize(new Dimension(300, 300));
        scrollBar = new JScrollPane(tableCustomers);
        this.add(scrollBar);
    }

    private void createActionListner() {
        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        createPanelAddCustomer();
                    }
                }
        );
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (tableCustomers.isRowSelected(tableCustomers.getSelectedRow()) == true) {
                    createPanelUpdateCustomer();
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

        insertName = new JLabel("Wprowadź imię :");
        insertLastName = new JLabel("Wprowadź nazwisko");
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

        name.setColumns(10);
        lastName.setColumns(10);
        adres.setColumns(10);
        number.setColumns(10);
        zipCode.setColumns(10);
        street.setColumns(10);

        jDialog.setLayout(new FlowLayout());
        jDialog.setSize(250, 300);
        jDialog.setTitle("Panel dodawania klienta");
        jDialog.setLocationRelativeTo(null);

        jDialog.add(insertName);
        jDialog.add(name);
        jDialog.add(insertLastName);
        jDialog.add(lastName);
        jDialog.add(insertAdress);
        jDialog.add(adres);
        jDialog.add(insertStreet);
        jDialog.add(street);
        jDialog.add(insertNumber);
        jDialog.add(number);
        jDialog.add(insertZipCode);
        jDialog.add(zipCode);
        jDialog.add(confirm);

        jDialog.setVisible(true);

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

        JTextField ulica;
        JTextField code;
        JTextField numbers;

        insertName = new JLabel("Wprowadź imię :");
        insertLastName = new JLabel("Wprowadź nazwisko");
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

        name.setColumns(10);
        lastName.setColumns(10);
        adres.setColumns(10);
        ulica.setColumns(10);
        numbers.setColumns(10);
        code.setColumns(10);

        jDialog.setLayout(new FlowLayout());
        jDialog.setSize(250, 300);
        jDialog.setTitle("Panel modyfikacji klienta");
        jDialog.setLocationRelativeTo(null);


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

        jDialog.add(insertName);
        jDialog.add(name);
        jDialog.add(insertLastName);
        jDialog.add(lastName);
        jDialog.add(insertAdress);
        jDialog.add(adres);
        jDialog.add(insertStreet);
        jDialog.add(ulica);
        jDialog.add(insertNumber);
        jDialog.add(numbers);
        jDialog.add(insertZipCode);
        jDialog.add(code);

        jDialog.add(confirm);
        jDialog.setVisible(true);


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

