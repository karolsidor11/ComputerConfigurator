package panels;

import daoimpl.CustomerDAOTempImpl;
import frame.MainFrame;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerPanel extends JPanel {

    private JButton addButton;
    private JButton deleteButton;
    private JButton back;
    private JTable tableCustomers;
    private JScrollPane scrollBar;
    private JToolBar toolBarButton;


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
        addButton = new JButton("Dodaj klienta");
        deleteButton = new JButton("Usuń klienta");
        back = new JButton("Wstecz");
        toolBarButton = new JToolBar();
        toolBarButton.add(back);
        toolBarButton.add(addButton);
        toolBarButton.add(deleteButton);
        this.add(toolBarButton);
    }

    private void createTable() {
        CustomerDAOTempImpl dao = new CustomerDAOTempImpl();
        List<Customer> allCustomerList = dao.getAllCustomerList();
        String[] columnNames = {"id", "imię", "nazwisko", "adres"};
        DefaultTableModel modelCustomer = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < allCustomerList.size(); i++) {
            Integer id = allCustomerList.get(i).getId();
            String name = allCustomerList.get(i).getName();
            String lastname = allCustomerList.get(i).getLastname();
            String adres = allCustomerList.get(i).getAdres();
            Object[] customer = {id, name, lastname, adres};
            modelCustomer.addRow(customer);
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
                        JDialog jDialog = new JDialog();
                        jDialog.setSize(200, 200);
                        jDialog.setVisible(true);
                    }
                }
        );

        deleteButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton delete = new JButton("Usuń");
                        JTextField jTextField = new JTextField("");
                        JLabel label = new JLabel("Wprowadź id klienta");

                        JDialog jDialog = new JDialog();
                        jDialog.setTitle("Usuwanie klienta");
                        jDialog.setSize(300, 150);
                        jDialog.setLocationByPlatform(true);
                        jDialog.setResizable(false);

                        jDialog.setLayout(new FlowLayout());

                        jTextField.setPreferredSize(new Dimension(90, 20));
                        delete.setPreferredSize(new Dimension(90, 25));

                        jDialog.add(label);
                        jDialog.add(jTextField);
                        jDialog.add(delete);

                        jDialog.pack();
                        jDialog.setVisible(true);

                        delete.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                // Do sprawdzenia  niestety nie działa
                                String text = jTextField.getText();
                                CustomerDAOTempImpl daoTemp = new CustomerDAOTempImpl();
                                daoTemp.deleteCustomer(Integer.valueOf(text));
                                System.out.println(text);
                                List<Customer> list = daoTemp.getAllCustomerList();


                                for(Customer lista  : list){
                                    System.out.println(lista.getName());
                                }


                                String[] names = {"id", "imię", "nazwisko", "adres"};
                                DefaultTableModel defaultTableModel = new DefaultTableModel(names, 0);
                                for (int i = 0; i < list.size(); i++) {
                                    Integer id = list.get(i).getId();
                                    String name = list.get(i).getName();
                                    String surname = list.get(i).getLastname();
                                    String adres = list.get(i).getAdres();
                                    Object[] rows = {id, name, surname, adres};
                                    defaultTableModel.addRow(rows);
                                }
                                JTable jTable = new JTable(defaultTableModel);
                                jTable.setBackground(Color.YELLOW);
                                jTable.setPreferredScrollableViewportSize(new Dimension(300, 300));
                                scrollBar = new JScrollPane(jTable);
                                add(scrollBar);

                               // createTable();

                                jDialog.dispose();

                            }
                        });

                    }
                }
        );
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
                try {
                    MainFrame mainFrame = new MainFrame();
                } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
        });

    }
}

