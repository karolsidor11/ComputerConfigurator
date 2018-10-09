package panels;

import daoimpl.ComputerComponentJPA;
import daoimpl.ComputerSetJPA;
import daoimpl.CustomerJPA;
import model.ComputerComponent;
import model.ComputerSet;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddComputerSetPanel extends JDialog {
//TODO nazwy zmiennych do poprawy
    private JLabel nazwa;
    private JLabel name;
    private JLabel client;
    private JLabel component;
    private JLabel priceSet;
    private JTextField nameSet;
    private JTextField jTextField;
    private JTextField nazwaSet;
    private JTextField allPrice;
    private JButton button;
    private JButton jButton;
    private JComboBox comboClient;
    private JComboBox comboComponent;
    private JTextArea jTextArea;
    private Font font;
    private GridBagConstraints gbc;
    private ComputerComponentJPA computerComponentJPA;
    private List<ComputerComponent> zamowienie = new ArrayList<>();
    private DefaultTableModel model;
    private String status;
    private String title;
    private JTable tableSet;


    public AddComputerSetPanel(DefaultTableModel model, String status, String title, JTable tableSet) {
        this.model = model;
        this.status = status;
        this.title = title;
        this.tableSet = tableSet;

        createFrame();
        createComponents();
        addComponents();
        setTitle(title);
        actionButtons();
    }

    private void createFrame() {
        setTitle("Panel tworzenia zamówienia");
        setSize(new Dimension(457, 360));
        setLocationByPlatform(true);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        setVisible(true);
    }

    private void createComponents() {
        nazwa = new JLabel("Wprowadź nazwę zamówienia :");
        name = new JLabel("Wprowadź opis zamówienia :");
        client = new JLabel("Wybierz klienta : ");
        component = new JLabel("Wybierz podzespół PC :");
        priceSet = new JLabel("Cena zestawu komputerowego :");

        nameSet = new JTextField();
        jTextField = new JTextField();
        nazwaSet = new JTextField();
        allPrice = new JTextField();
        comboClient = new JComboBox();
        comboComponent = new JComboBox();
        jTextArea = new JTextArea();
        button = new JButton("+");
        jButton = new JButton("Zatwierdź");
        font = new Font(Font.DIALOG, Font.PLAIN, 12);

        jTextField.setColumns(15);
        nameSet.setColumns(15);
        allPrice.setColumns(15);
        nazwaSet.setColumns(15);
        button.setToolTipText("Dodaj nowy podzespół");
        jButton.setToolTipText("Zatwierdź");
        jTextArea.setColumns(15);
        jTextArea.setRows(8);
        jTextArea.setFont(font);
        jTextArea.setEnabled(false);

        jTextField.setCaretPosition(0);
        comboClient.setPreferredSize(new Dimension(230, 22));
        comboComponent.setPreferredSize(new Dimension(130, 22));
        button.setPreferredSize(new Dimension(42, 20));
    }

    private void addComponents() {
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(nazwa, gbc);
        gbc.gridx++;
        add(nazwaSet, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        add(name, gbc);
        gbc.gridx++;
        add(nameSet, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        add(client, gbc);
        gbc.gridx++;
        add(comboClient, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        add(component, gbc);
        gbc.gridx++;
        add(comboComponent, gbc);
        gbc.gridx++;
        add(button, gbc);

        gbc.gridy++;
        gbc.gridx = 1;

        add(jTextArea, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        add(priceSet, gbc);
        gbc.gridx++;
        add(allPrice, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.weighty++;
        gbc.insets = new Insets(12, 0, 12, 0);
        add(jButton, gbc);
        setResizable(false);
        pack();
    }

    public void initValue() {
        CustomerJPA customerJPA = new CustomerJPA();
        java.util.List<Customer> customers = customerJPA.allCustomer();

        for (Customer a : customers) {
            String customerName = a.getName();
            comboClient.addItem(a);
        }
        comboClient.setSelectedItem(null);

        ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();

        List<ComputerComponent> computerComponents = computerComponentJPA.allComputerComponent();

        for (ComputerComponent components : computerComponents) {
            String componentName = components.getComponentName();
            comboComponent.addItem(componentName);
        }
        comboComponent.setSelectedItem(null);
    }

    private void actionButtons() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String selectedItem = (String) comboComponent.getModel().getSelectedItem();
                Integer id = 0;
                int cena = 0;
                int componentPrice = 0;

                computerComponentJPA = new ComputerComponentJPA();
                List<ComputerComponent> computerComponents = computerComponentJPA.allComputerComponent();

                for (ComputerComponent B : computerComponents) {

                    if (B.getComponentName().equals(selectedItem)) {
                        id = B.getId();
                    }
                }
                ComputerComponentJPA computerComponentJPA1 = new ComputerComponentJPA();
                ComputerComponent byId = computerComponentJPA1.getById(id);

                zamowienie.add(byId);

                for (ComputerComponent cp : zamowienie) {

                    componentPrice = cp.getPrice().intValue();
                    cena = cena + componentPrice;

                    System.out.println(cena);
                    allPrice.setText(String.valueOf(cena));
                    comboComponent.setSelectedItem(null);
                }
                jTextArea.insert(selectedItem + "-" + componentPrice + "zł" + "\n", 0);
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (status.equals("Add")) {//TODO wydziel status do klasy ENUM.
                    addNewSet();
                }
                if (status.equals("Update")) {
                    modifySet();
                }
            }
        });
    }

    private void addNewSet() {

        ComputerSetJPA computerSetJPA = new ComputerSetJPA();
        ComputerSet computerSet = new ComputerSet();

        try {

            computerSet.setComputerSetName(nazwaSet.getText());
            computerSet.setComputerSetDescribe(nameSet.getText());
            computerSet.setComputerPrice(BigDecimal.valueOf(Integer.parseInt(allPrice.getText())));
            computerSet.setCustomer((model.Customer) comboClient.getSelectedItem());

            //Wyrzuca błędy !!!! ->

            //   computerSet.setComputerComponentList(zamowienie);

            // <-

            if (!computerSet.getComputerSetName().equals("") && !computerSet.getComputerSetDescribe().equals("")) {

                computerSetJPA.addComputerSet(computerSet);

                Object[] rows = new Object[5];

                rows[0] = computerSet.getId();
                rows[1] = nazwaSet.getText();
                rows[2] = nameSet.getText();
                rows[3] = allPrice.getText();
                rows[4] = comboClient.getSelectedItem();

                model.addRow(rows);

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź poprawnie wszystkie dane !!! ");
            }

        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, "Wprowadź poprawnie wszystkie dane !!! ");
            e1.printStackTrace();
        }
    }

    private void modifySet() {

        int selectedRow = tableSet.getSelectedRow();

        ComputerSetJPA computerSetJPA = new ComputerSetJPA();
        String valueAt = tableSet.getModel().getValueAt(selectedRow, 0).toString();
        ComputerSet setById = computerSetJPA.getSetById(Integer.parseInt(valueAt));


        try {
            setById.setId(Integer.parseInt(valueAt));
            setById.setComputerSetName(nazwaSet.getText());
            setById.setComputerSetDescribe(nameSet.getText());
            setById.setComputerPrice(BigDecimal.valueOf(Integer.parseInt(allPrice.getText())));


            if (!setById.getComputerSetName().equals("") && !setById.getComputerSetDescribe().equals("")
                    && !setById.getCustomer().equals("")) {

                computerSetJPA.mergeComputerSet(setById);

                int i = tableSet.getSelectedRow();
                model.setValueAt(setById.getId(), i, 0);
                model.setValueAt(nazwaSet.getText(), i, 1);
                model.setValueAt(nameSet.getText(), i, 2);
                model.setValueAt(allPrice.getText(), i, 3);
                model.setValueAt(comboClient.getSelectedItem(), i, 4);

                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Wprowadź poprawnie wszystkie dane !!! ");
            }

        } catch (Exception e1) {

            JOptionPane.showMessageDialog(null, "Wprowadź poprawnie wszystkie dane !!! ");
        }
    }

    public void initUpdateValue() {

        int a = tableSet.getSelectedRow();

        CustomerJPA customerJPA = new CustomerJPA();
        List<model.Customer> customers = customerJPA.allCustomer();
        ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
        List<ComputerComponent> computerComponents = computerComponentJPA.allComputerComponent();

        BigDecimal valueAt = (BigDecimal) model.getValueAt(a, 3);

        int i = valueAt.intValue();


        nazwaSet.setText((String) model.getValueAt(a, 1));
        nameSet.setText((String) model.getValueAt(a, 2));
        allPrice.setText(String.valueOf(i));

        Customer valueAt1 = (Customer) model.getValueAt(a, 4);

        for (Customer cusom : customers) {
            String name = cusom.toString();
            comboClient.addItem(name);
            comboClient.setSelectedItem(valueAt1.toString());
        }
        for (ComputerComponent component : computerComponents) {
            comboComponent.addItem(component.getComponentName());
            comboComponent.setSelectedItem(null);
        }

        for (ComputerComponent comp : computerComponents) {

            //jTextArea.insert(comp.getComponentName() + "-" + comp.getPrice() + " zł" + "\n", 0);
        }
    }
}
