package panels;

import daoimpl.CustomerDAOTempImpl;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerPanel extends  JPanel{

private JButton addButton;
private JButton deleteButton;
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

    private void createToolBarButton(){
        addButton = new JButton("Dodaj klienta");
        deleteButton = new JButton("Usuń klienta");
        toolBarButton = new JToolBar();
        toolBarButton.add(addButton);
        toolBarButton.add(deleteButton);
        this.add(toolBarButton);
    }

    private void createTable(){
        CustomerDAOTempImpl dao = new CustomerDAOTempImpl();
        List<Customer> allCustomerList = dao.getAllCustomerList();
        String[] columnNames = {"id","imię","nazwisko","adres"};
        DefaultTableModel modelCustomer = new DefaultTableModel(columnNames,0);
        for (int i = 0; i < allCustomerList.size(); i++) {
            Integer id = allCustomerList.get(i).getId();
            String name = allCustomerList.get(i).getName();
            String lastname = allCustomerList.get(i).getLastname();
            String adres = allCustomerList.get(i).getAdres();
            Object[] customer = {i,name,lastname,adres};
            modelCustomer.addRow(customer);
        }

        tableCustomers = new JTable(modelCustomer);
        tableCustomers.setBackground(Color.YELLOW);
        tableCustomers.setPreferredScrollableViewportSize(new Dimension(300,300));
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
                    //usuń klienta
                    }
                }
        );
    }
}

