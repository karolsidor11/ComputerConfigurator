package panels;

import daoimpl.ComputerSetDAOImpl;
import frame.MainFrame;
import model.ComputerSet;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class ComputerSetPanel extends JPanel {

    private JButton addSet;
    private JButton deleteSet;
    private JButton update;
    private JButton back;
    private JToolBar toolBar;
    private JTable tableSet;
    private JScrollPane jScrollPane;
    private DefaultTableModel model;
    private JDialog jDialog;
    private JLabel insertId, insertSetName, insertSetDescription, insertPrice, insertCustomer;
    private JTextField id, setName, setDescription, setPrice, Customer;
    private JButton confirm;


    public ComputerSetPanel() {
        createComponent();
        createTable();
        createToolBarButton();
        createActionListner();

    }

    private void createComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    }

    private void createTable() {
        String[] columnName = {"id", "Nazwa", "Opis", "Cena", "Klient"};
        model = new DefaultTableModel(columnName, 0);
        ComputerSetDAOImpl dao = new ComputerSetDAOImpl();
        List<ComputerSet> computerSetList = dao.getAllComputerSets();
        for (int i = 0; i < computerSetList.size(); i++) {
            Integer id = computerSetList.get(i).getId();
            String computerSetName = computerSetList.get(i).getComputerSetName();
            String computerSetDescribe = computerSetList.get(i).getComputerSetDescribe();
            BigDecimal computerPrice = computerSetList.get(i).getComputerPrice();
            Customer customer = computerSetList.get(i).getCustomer();
            Object[] obj = {id, computerSetName, computerSetDescribe, computerPrice, customer.getName()};
            model.addRow(obj);
        }
        tableSet = new JTable(model);
        tableSet.setBackground(Color.YELLOW);
        tableSet.setPreferredScrollableViewportSize(new Dimension(300, 300));
        jScrollPane = new JScrollPane(tableSet);
        this.add(jScrollPane);

    }

    private void createActionListner() {
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent jComponent = (JComponent) e.getSource();
                Window window = SwingUtilities.getWindowAncestor(jComponent);
                window.dispose();
                backToMyFrame();
            }
        });
        addSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanelAddComputerSet();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableSet.isRowSelected(tableSet.getSelectedRow()) == true) {
                    createPanelUpdateComputerSet();
                } else {
                    JOptionPane.showMessageDialog(null, "Wybierz zestaw komputerowy do modyfikacji!");
                }
            }
        });
        deleteSet.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createPanelDeleteComputerSet();
            }
        });
    }

    private void createToolBarButton() {
        addSet = new JButton("Dodaj ");
        update = new JButton("Modyfikuj");
        deleteSet = new JButton("Usuń ");
        back = new JButton("Wstecz");
        toolBar = new JToolBar();
        toolBar.add(back);
        toolBar.add(addSet);
        toolBar.add(update);
        toolBar.add(deleteSet);
        this.add(toolBar);

    }

    private void backToMyFrame() {
        try {
            MainFrame mainFrame = new MainFrame();
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException e1) {
            e1.printStackTrace();
        }
    }

    private void createPanelAddComputerSet() {
        jDialog = new JDialog();
        id = new JTextField();
        setName = new JTextField();
        setDescription = new JTextField();
        setPrice = new JTextField();
        Customer = new JTextField();
        insertId = new JLabel("Wprowadź id produktu: ");
        insertSetName = new JLabel("Wprowadź nazwę produktu: ");
        insertSetDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        insertCustomer = new JLabel("Wprowadż klienta: ");
        confirm = new JButton("Zatwierdź");

        setName.setColumns(10);
        setDescription.setColumns(10);
        setPrice.setColumns(10);
        id.setColumns(10);
        Customer.setColumns(10);


        jDialog.setTitle("Panel dodawania zestawu komputerowego ");
        jDialog.setSize(320, 300);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());

        jDialog.add(insertId);
        jDialog.add(id);
        jDialog.add(insertSetName);
        jDialog.add(setName);
        jDialog.add(insertSetDescription);
        jDialog.add(setDescription);
        jDialog.add(insertPrice);
        jDialog.add(setPrice);
        jDialog.add(insertCustomer);
        jDialog.add(Customer);
        jDialog.add(confirm);


        jDialog.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object[] rows = new Object[5];
                rows[0] = id.getText();
                rows[1] = setName.getText();
                rows[2] = setDescription.getText();
                rows[3] = setPrice.getText();
                rows[4] = Customer.getText();

                model.addRow(rows);
                jDialog.dispose();
            }
        });

    }

    private void createPanelUpdateComputerSet() {
        jDialog = new JDialog();
        id = new JTextField();
        setName = new JTextField();
        setDescription = new JTextField();
        setPrice = new JTextField();
        Customer = new JTextField();
        insertId = new JLabel("Wprowadź id produktu: ");
        insertSetName = new JLabel("Wprowadź nazwę produktu: ");
        insertSetDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        insertCustomer = new JLabel("Wprowadż klienta: ");
        confirm = new JButton("Zatwierdź");

        setName.setColumns(10);
        setDescription.setColumns(10);
        setPrice.setColumns(10);
        id.setColumns(10);
        Customer.setColumns(10);

        int a = tableSet.getSelectedRow();

        id.setText(String.valueOf(model.getValueAt(a, 0)));
        setName.setText((String) model.getValueAt(a, 1));
        setDescription.setText((String) model.getValueAt(a, 2));
        setPrice.setText(String.valueOf(model.getValueAt(a, 3)));
        Customer.setText((String) model.getValueAt(a, 4));


        jDialog.setTitle("Panel modyfikacji  zestawu komputerowego ");
        jDialog.setSize(320, 300);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());

        jDialog.add(insertId);
        jDialog.add(id);
        jDialog.add(insertSetName);
        jDialog.add(setName);
        jDialog.add(insertSetDescription);
        jDialog.add(setDescription);
        jDialog.add(insertPrice);
        jDialog.add(setPrice);
        jDialog.add(insertCustomer);
        jDialog.add(Customer);
        jDialog.add(confirm);


        jDialog.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int i = tableSet.getSelectedRow();
                model.setValueAt(id.getText(), i, 0);
                model.setValueAt(setName.getText(), i, 1);
                model.setValueAt(setDescription.getText(), i, 2);
                model.setValueAt(setPrice.getText(), i, 3);
                model.setValueAt(Customer.getText(), i, 4);

                jDialog.dispose();

            }
        });

    }

    private void createPanelDeleteComputerSet() {

        int i = tableSet.getSelectedRow();
        if (i >= 0) {
            model.removeRow(i);
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zestaw komputerowy do usunięcia !");
        }

    }
}
