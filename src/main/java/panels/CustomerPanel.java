package panels;

import daoimpl.CustomerJPA;
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

