package panels;

import daoimpl.ComputerComponentJPA;
import daoimpl.ComputerSetJPA;
import daoimpl.CustomerJPA;
import frame.MainFrame;
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
    private Font font;
    private List<ComputerComponent> zamowienie = new ArrayList<>();

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

        ComputerSetJPA computerSetJPA = new ComputerSetJPA();
        List<ComputerSet> computerSets = computerSetJPA.allList();

        model = new DefaultTableModel(columnName, 0);
        for (int i = 0; i < computerSets.size(); i++) {
            Integer id = computerSets.get(i).getId();
            String computerSetName = computerSets.get(i).getComputerSetName();
            String computerSetDescribe = computerSets.get(i).getComputerSetDescribe();
            BigDecimal computerPrice = computerSets.get(i).getComputerPrice();
            model.Customer customer = computerSets.get(i).getCustomer();
            Object[] objects = {id, computerSetName, computerSetDescribe, computerPrice, customer};

            model.addRow(objects);
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
                // createPanelAddComputerSet();
                createNewSet();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableSet.isRowSelected(tableSet.getSelectedRow()) == true) {
                    // createPanelUpdateComputerSet();
                    newUpdate();
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
        font = new Font(Font.DIALOG, Font.PLAIN, 12);
        addSet.setFont(font);
        update.setFont(font);
        deleteSet.setFont(font);
        back.setFont(font);
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

            ComputerSetJPA computerSetJPA = new ComputerSetJPA();

            String valueAt = tableSet.getModel().getValueAt(i, 0).toString();
            ComputerSet setById = computerSetJPA.getSetById(Integer.parseInt(valueAt));
            computerSetJPA.removeSet(setById);

            model.removeRow(i);
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zestaw komputerowy do usunięcia !");
        }
    }


    private void createNewSet() {

        JDialog jDialog = new JDialog();
        jDialog.setTitle("Panel tworzenia zamówienia");
        jDialog.setSize(new Dimension(340, 300));
        jDialog.setLocationByPlatform(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new GridBagLayout());
        jDialog.setVisible(true);


        JLabel nazwa = new JLabel("Wprowadź nazwę zamówienia :");
        JLabel name = new JLabel("Wprowadź opis zamówienia :");
        JLabel client = new JLabel("Wybierz klienta : ");
        JLabel component = new JLabel("Wybierz podzespół PC :");
        JLabel priceSet = new JLabel("Cena zestawu komputerowego :");


        JTextField nameSet = new JTextField();
        JTextField jTextField = new JTextField();
        JTextField nazwaSet = new JTextField();
        JTextField allPrice = new JTextField();
        JComboBox comboClient = new JComboBox();
        JComboBox comboComponent = new JComboBox();
        JTextArea jTextArea = new JTextArea();
        JButton button = new JButton("+");


        CustomerJPA customerJPA = new CustomerJPA();
        List<model.Customer> customers = customerJPA.allCustomer();

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


        JButton jButton = new JButton("Zatwierdź");

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


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        jDialog.add(nazwa, gbc);
        gbc.gridx++;
        jDialog.add(nazwaSet, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(name, gbc);
        gbc.gridx++;
        jDialog.add(nameSet, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(client, gbc);
        gbc.gridx++;
        jDialog.add(comboClient, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(component, gbc);
        gbc.gridx++;
        jDialog.add(comboComponent, gbc);
        gbc.gridx++;
        jDialog.add(button, gbc);

        gbc.gridy++;
        gbc.gridx = 1;

        jDialog.add(jTextArea, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(priceSet, gbc);
        gbc.gridx++;
        jDialog.add(allPrice, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.weighty++;
        gbc.insets = new Insets(12, 0, 12, 0);
        jDialog.add(jButton, gbc);
        jDialog.setResizable(false);
        jDialog.pack();


        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String selectedItem = (String) comboComponent.getModel().getSelectedItem();
                Integer id = 0;
                int cena = 0;
                int componentPrice = 0;


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


        comboComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object selectedItem = comboComponent.getSelectedItem();

                ComputerComponentJPA computerComponentJPA1 = new ComputerComponentJPA();
                List<ComputerComponent> computerComponents1 = computerComponentJPA1.allComputerComponent();

//                for (ComputerComponent component : computerComponents1) {
//                    if (component.getComponentName().equals(selectedItem) == true) {
//
//                        BigDecimal price = component.getPrice();
//                        int i = price.intValue();
//                        allPrice.setText(String.valueOf(i));
//                    }
                //}
            }
        });
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComputerSetJPA computerSetJPA = new ComputerSetJPA();
                ComputerSet computerSet = new ComputerSet();

                try {

                    computerSet.setComputerSetName(nazwaSet.getText());
                    computerSet.setComputerSetDescribe(nameSet.getText());
                    computerSet.setComputerPrice(BigDecimal.valueOf(Integer.parseInt(allPrice.getText())));
                    computerSet.setCustomer((model.Customer) comboClient.getSelectedItem());

                    //Wyrzuca błędy !!!! ->

                    computerSet.setComputerComponentList(zamowienie);

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

                        jDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!! ");
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!! ");
                    e1.printStackTrace();
                }

            }
        });
    }

    private void newUpdate() {

        JDialog jDialog = new JDialog();
        jDialog.setTitle("Panel modyfikacji  zestawu komputerowego ");
        jDialog.setSize(new Dimension(340, 300));
        jDialog.setLocationRelativeTo(null);
        jDialog.setLocationByPlatform(true);
        jDialog.setLayout(new GridBagLayout());
        jDialog.setVisible(true);


        JComboBox klient = new JComboBox();
        JComboBox element = new JComboBox();
        JButton addComponent = new JButton("+");
        JLabel podzespół = new JLabel("Wybierz podzespół :");
        JTextArea jTextArea = new JTextArea();


        klient.setPreferredSize(new Dimension(230, 22));
        element.setPreferredSize(new Dimension(130, 22));
        addComponent.setSize(new Dimension(42, 22));
        addComponent.setToolTipText("Dodaj nowy podzespół");

        id = new JTextField();
        setName = new JTextField();
        setDescription = new JTextField();
        setPrice = new JTextField();
        insertSetName = new JLabel("Wprowadź nazwę produktu: ");
        insertSetDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        insertCustomer = new JLabel("Wprowadż klienta: ");
        confirm = new JButton("Zatwierdź");

        setName.setColumns(15);
        setDescription.setColumns(15);
        setPrice.setColumns(15);
        confirm.setToolTipText("Zatwierdź");
        jTextArea.setEnabled(false);
        jTextArea.setRows(8);
        jTextArea.setColumns(15);
        jTextArea.setFont(font);

        int a = tableSet.getSelectedRow();

        CustomerJPA customerJPA = new CustomerJPA();
        List<model.Customer> customers = customerJPA.allCustomer();
        ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
        List<ComputerComponent> computerComponents = computerComponentJPA.allComputerComponent();

        BigDecimal valueAt = (BigDecimal) model.getValueAt(a, 3);

        int i = valueAt.intValue();


        setName.setText((String) model.getValueAt(a, 1));
        setDescription.setText((String) model.getValueAt(a, 2));
        setPrice.setText(String.valueOf(i));

        for (Customer cusom : customers) {
            String name = cusom.getName();
            klient.addItem(name);
        }
        for (ComputerComponent component : computerComponents) {
            String componentName = component.getComponentName();
            element.addItem(componentName);
        }

        for (ComputerComponent comp : computerComponents) {

            jTextArea.insert(comp.getComponentName() + "-" + comp.getPrice() + " zł" + "\n", 0);
        }


        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        jDialog.add(insertSetName, gbc);
        gbc.gridx++;
        jDialog.add(setName, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertSetDescription, gbc);
        gbc.gridx++;
        jDialog.add(setDescription, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertCustomer, gbc);
        gbc.gridx++;
        jDialog.add(klient, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(podzespół, gbc);
        gbc.gridx++;
        jDialog.add(element, gbc);
        gbc.gridx++;
        jDialog.add(addComponent, gbc);

        gbc.gridx = 1;
        gbc.gridy++;

        jDialog.add(jTextArea, gbc);

        gbc.gridy++;
        gbc.gridx = 0;

        jDialog.add(insertPrice, gbc);
        gbc.gridx++;
        jDialog.add(setPrice, gbc);

        gbc.gridy++;
        gbc.gridx = 1;
        gbc.weighty++;
        gbc.insets = new Insets(12, 0, 12, 0);

        jDialog.add(confirm, gbc);
        jDialog.setResizable(false);
        jDialog.pack();


        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int selectedRow = tableSet.getSelectedRow();

                ComputerSetJPA computerSetJPA = new ComputerSetJPA();
                String valueAt = tableSet.getModel().getValueAt(selectedRow, 0).toString();
                ComputerSet setById = computerSetJPA.getSetById(Integer.parseInt(valueAt));


                try {

                    setById.setId(Integer.parseInt(valueAt));
                    setById.setComputerSetName(setName.getText());
                    setById.setComputerSetDescribe(setDescription.getText());
                    setById.setComputerPrice(BigDecimal.valueOf(Integer.parseInt(setPrice.getText())));


                    if (!setById.getComputerSetName().equals("") && !setById.getComputerSetDescribe().equals("")
                            && !setById.getCustomer().equals("")) {

                        computerSetJPA.mergeComputerSet(setById);

                        int i = tableSet.getSelectedRow();
                        model.setValueAt(setById.getId(), i, 0);
                        model.setValueAt(setName.getText(), i, 1);
                        model.setValueAt(setDescription.getText(), i, 2);
                        model.setValueAt(setPrice.getText(), i, 3);
                        model.setValueAt(klient.getSelectedItem(), i, 4);

                        jDialog.dispose();
                    } else {
                        JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!! ");
                    }

                } catch (Exception e1) {

                    JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!! ");
                }


            }
        });

        addComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String selectedItem = (String) element.getModel().getSelectedItem();
                Integer id = 0;
                int cena = 0;

                for (ComputerComponent B : computerComponents) {

                    if (B.getComponentName().equals(selectedItem)) {
                        id = B.getId();
                    }
                }
                ComputerComponentJPA computerComponentJPA1 = new ComputerComponentJPA();
                ComputerComponent byId = computerComponentJPA1.getById(id);

                zamowienie.add(byId);
                int componentPrice = 0;

                for (ComputerComponent cp : zamowienie) {

                    componentPrice = cp.getPrice().intValue();
                    cena = cena + componentPrice;

                    System.out.println(cena);
                    setPrice.setText(String.valueOf(cena));
                    element.setSelectedItem(null);

                }

                jTextArea.insert(selectedItem + "-" + zamowienie.get(0).getPrice() + " zł" + "\n", 0);

            }
        });
        element.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


//                String selectedItem = element.getModel().getSelectedItem().toString();
//
//                ComputerComponentJPA computerComponentJPA1 = new ComputerComponentJPA();
//
//                List<ComputerComponent> computerComponents1 = computerComponentJPA1.allComputerComponent();
//
//                for (ComputerComponent component : computerComponents1) {
//
//                    if (component.getComponentName().equals(selectedItem) == true) {
//
//                        BigDecimal price = component.getPrice();
//                        int i = price.intValue();
//                        setPrice.setText(String.valueOf(i));
//                    }
//                }

            }
        });
    }

}
