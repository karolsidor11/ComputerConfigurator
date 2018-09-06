package panels;

import daoHibernate.CustomerJPA;
import daoimpl.CustomerDAOTempImpl;
import frame.MainFrame;
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

    private JLabel insertID, insertName, insertLastName, insertAdress;
    private JTextField id, name, lastName, adres;
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
        CustomerDAOTempImpl dao = new CustomerDAOTempImpl();
        List<Customer> allCustomerList = dao.getAllCustomerList();

        CustomerJPA customerJPA = new CustomerJPA();
        List<Customer> customers = customerJPA.allCustomer();

        String[] columnNames = {"id", "imię", "nazwisko", "adres"};
        modelCustomer = new DefaultTableModel(columnNames, 0);
//        for (int i = 0; i < allCustomerList.size(); i++) {
//            Integer id = allCustomerList.get(i).getId();
//            String name = allCustomerList.get(i).getName();
//            String lastname = allCustomerList.get(i).getLastname();
//            String adres = allCustomerList.get(i).getAdres();
//            Object[] customer = {id, name, lastname, adres};
//            modelCustomer.addRow(customer);
//        }

        for (int i = 0; i < customers.size(); i++) {
            Integer id = customers.get(i).getId();
            String name = customers.get(i).getName();
            String lastName = customers.get(i).getLastname();
            String adress = customers.get(i).getAdres();

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

        insertID = new JLabel("Wprowadż id : ");
        insertName = new JLabel("Wprowadź imię :");
        insertLastName = new JLabel("Wprowadź nazwisko");
        insertAdress = new JLabel("Wprowadź adres : ");

        id = new JTextField();
        name = new JTextField();
        lastName = new JTextField();
        adres = new JTextField();
        confirm = new JButton("Zatwierdź");

        id.setColumns(10);
        name.setColumns(10);
        lastName.setColumns(10);
        adres.setColumns(10);

        jDialog.setLayout(new FlowLayout());
        jDialog.setSize(250, 300);
        jDialog.setTitle("Panel dodawania klienta");
        jDialog.setLocationRelativeTo(null);


        jDialog.add(insertID);
        jDialog.add(id);
        jDialog.add(insertName);
        jDialog.add(name);
        jDialog.add(insertLastName);
        jDialog.add(lastName);
        jDialog.add(insertAdress);
        jDialog.add(adres);
        jDialog.add(confirm);

        jDialog.setVisible(true);


        int rowCount = modelCustomer.getRowCount();
        int number = rowCount + 1;
        id.setText(String.valueOf(number));
        id.setEnabled(false);
        Object[] row = new Object[4];

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = id.getText();
                row[1] = name.getText();
                row[2] = lastName.getText();
                row[3] = adres.getText();

                modelCustomer.addRow(row);

                CustomerJPA customerJPA = new CustomerJPA();
                Customer customer = new Customer();
                customer.setName(name.getText());
                customer.setLastname(lastName.getText());
                customer.setAdres(adres.getText());
                customerJPA.addCustomer(customer);
                jDialog.dispose();
            }
        });
    }

    private void createPanelUpdateCustomer() {

        JDialog jDialog = new JDialog();

        insertID = new JLabel("Wprowadż id : ");
        insertName = new JLabel("Wprowadź imię :");
        insertLastName = new JLabel("Wprowadź nazwisko");
        insertAdress = new JLabel("Wprowadź adres : ");

        id = new JTextField();
        name = new JTextField();
        lastName = new JTextField();
        adres = new JTextField();
        confirm = new JButton("Zatwierdź");

        id.setColumns(10);
        name.setColumns(10);
        lastName.setColumns(10);
        adres.setColumns(10);

        jDialog.setLayout(new FlowLayout());
        jDialog.setSize(250, 300);
        jDialog.setTitle("Panel modyfikacji klienta");
        jDialog.setLocationRelativeTo(null);


        int a = tableCustomers.getSelectedRow();


        // id.setText(String.valueOf((Integer) modelCustomer.getValueAt(a, 0)));
        name.setText((String) modelCustomer.getValueAt(a, 1));
        lastName.setText((String) modelCustomer.getValueAt(a, 2));
        adres.setText((String) modelCustomer.getValueAt(a, 3));


        jDialog.add(insertID);
        jDialog.add(id);
        jDialog.add(insertName);
        jDialog.add(name);
        jDialog.add(insertLastName);
        jDialog.add(lastName);
        jDialog.add(insertAdress);
        jDialog.add(adres);
        jDialog.add(confirm);

        jDialog.setVisible(true);

        id.setEnabled(false);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = tableCustomers.getSelectedRow();

                modelCustomer.setValueAt(id.getText(), i, 0);
                modelCustomer.setValueAt(name.getText(), i, 1);
                modelCustomer.setValueAt(lastName.getText(), i, 2);
                modelCustomer.setValueAt(adres.getText(), i, 3);


                CustomerJPA customerJPA = new CustomerJPA();
                Customer customer = new Customer();

                customer.setName(name.getText());
                customer.setLastname(lastName.getText());
                customer.setAdres(adres.getText());
                customerJPA.mergeCustomer(customer);

                jDialog.dispose();
            }
        });
    }

    private void createPanelDeleteCustomer() {
        Integer i = tableCustomers.getSelectedRow();
        if (i >= 0) {
            modelCustomer.removeRow(i);

            // Usuwanie klienta po ID - problem
            CustomerJPA customerJPA = new CustomerJPA();
            Customer byId = customerJPA.getById(i + 1);
            System.out.println(byId.getName());


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

