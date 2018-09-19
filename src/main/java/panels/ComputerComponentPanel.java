package panels;

import daoimpl.ComputerComponentJPA;
import frame.MainFrame;
import model.ComputerComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class ComputerComponentPanel extends JPanel {

    private JButton addComputerComponent;
    private JButton deleteComputerComponent;
    private JButton update;
    private JButton back;
    private JToolBar toolBarButton;
    private JTable tableComponent;
    private JScrollPane jScrollPane;
    private DefaultTableModel model;
    private JDialog jDialog;
    private JLabel insertComponentName, insertComponentDescription, insertPrice;
    private JTextField componentName, componentDescription, componentPrice;
    private JButton confirm;


    public ComputerComponentPanel() {
        createComponent();
        createTable();
        createToolBarButton();
        createActionListner();
    }

    private void createComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void createTable() {
        String[] columnNames = {"id", "Nazwa ", "Opis", "Cena"};

        ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
        List<ComputerComponent> computerComponents = computerComponentJPA.allComputerComponent();

        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < computerComponents.size(); i++) {
            Integer id = computerComponents.get(i).getId();
            String componentName = computerComponents.get(i).getComponentName();
            String componentDescribe = computerComponents.get(i).getComponentDescribe();
            BigDecimal price = computerComponents.get(i).getPrice();

            Object[] objects = {id, componentName, componentDescribe, price};

            model.addRow(objects);
        }


        tableComponent = new JTable(model);
        tableComponent.setBackground(Color.YELLOW);
        tableComponent.setPreferredScrollableViewportSize(new Dimension(300, 300));
        jScrollPane = new JScrollPane(tableComponent);
        this.add(jScrollPane);
    }

    private void createToolBarButton() {
        addComputerComponent = new JButton("Dodaj ");
        deleteComputerComponent = new JButton("Usuń ");
        update = new JButton("Modyfikuj");
        back = new JButton("Wstecz");
        toolBarButton = new JToolBar();
        toolBarButton.add(back);
        toolBarButton.add(addComputerComponent);
        toolBarButton.add(update);
        toolBarButton.add(deleteComputerComponent);
        this.add(toolBarButton);
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
        addComputerComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                createPanelAddComputerComponent();
            }
        });
        deleteComputerComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPanelDeleteComputerComponent();
            }

        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (tableComponent.isRowSelected(tableComponent.getSelectedRow()) == true) {
                    createPanelUpdateComputerComponent();
                } else {
                    JOptionPane.showMessageDialog(null, "Wybierz podzespół komputera do modyfikacji !");
                }

            }
        });

    }

    private void backToMyFrame() {
        try {
            MainFrame mainFrame = new MainFrame();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e1) {
            e1.printStackTrace();
        }
    }

    private void createPanelDeleteComputerComponent() {

        int i = tableComponent.getSelectedRow();
        if (i >= 0) {

            ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
            String valueAt = tableComponent.getModel().getValueAt(i, 0).toString();

            ComputerComponent byId = computerComponentJPA.getById(Integer.parseInt(valueAt));
            computerComponentJPA.removeComputerComponent(byId);

            model.removeRow(i);

        } else {
            JOptionPane.showMessageDialog(this, "Wybierz podzespół komputera do usunięcia !");
        }
    }

    private void createPanelAddComputerComponent() {

        jDialog = new JDialog();
        componentName = new JTextField();
        componentDescription = new JTextField();
        componentPrice = new JTextField();
        insertComponentName = new JLabel("Wprowadź nazwę produktu: ");
        insertComponentDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        confirm = new JButton("Zatwierdź");

        componentName.setColumns(10);
        componentDescription.setColumns(10);
        componentPrice.setColumns(10);


        jDialog.setTitle("Panel dodawania komponentu komputera");
        jDialog.setSize(320, 300);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());

        jDialog.add(insertComponentName);
        jDialog.add(componentName);
        jDialog.add(insertComponentDescription);
        jDialog.add(componentDescription);
        jDialog.add(insertPrice);
        jDialog.add(componentPrice);
        jDialog.add(confirm);


        jDialog.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
                ComputerComponent computerComponent = new ComputerComponent();

                try {
                    computerComponent.setComponentName(componentName.getText());
                    computerComponent.setComponentDescribe(componentDescription.getText());
                    computerComponent.setPrice(BigDecimal.valueOf(Integer.parseInt(componentPrice.getText())));

                    computerComponentJPA.addComputerComponent(computerComponent);

                    Object[] rows = new Object[4];
                    rows[0] = computerComponent.getId();
                    rows[1] = componentName.getText();
                    rows[2] = componentDescription.getText();
                    rows[3] = componentPrice.getText();

                    model.addRow(rows);
                    jDialog.dispose();

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!!");
                }

            }
        });
    }

    private void createPanelUpdateComputerComponent() {
        jDialog = new JDialog();
        componentName = new JTextField();
        componentDescription = new JTextField();
        componentPrice = new JTextField();
        insertComponentName = new JLabel("Wprowadź nazwę produktu: ");
        insertComponentDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        confirm = new JButton("Zatwierdź");

        componentName.setColumns(10);
        componentDescription.setColumns(10);
        componentPrice.setColumns(10);


        int a = tableComponent.getSelectedRow();
        componentName.setText((String) model.getValueAt(a, 1));
        componentDescription.setText((String) model.getValueAt(a, 2));
        componentPrice.setText(String.valueOf((BigDecimal) model.getValueAt(a, 3)));


        jDialog.setTitle("Panel modyfikacji komponentu komputera");
        jDialog.setSize(320, 300);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());


        jDialog.add(insertComponentName);
        jDialog.add(componentName);
        jDialog.add(insertComponentDescription);
        jDialog.add(componentDescription);
        jDialog.add(insertPrice);
        jDialog.add(componentPrice);
        jDialog.add(confirm);


        jDialog.setVisible(true);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tableComponent.getSelectedRow();

                ComputerComponentJPA computerComponentJPA = new ComputerComponentJPA();
                String valueAt = tableComponent.getModel().getValueAt(i, 0).toString();

                ComputerComponent byId = computerComponentJPA.getById(Integer.parseInt(valueAt));

                try {
                    byId.setComponentName(componentName.getText());
                    byId.setComponentDescribe(componentDescription.getText());
                    byId.setPrice(BigDecimal.valueOf(Integer.parseInt(componentPrice.getText())));


                    if (!componentName.getText().equals("") && !componentDescription.getText().equals("")) {

                        computerComponentJPA.mergeComponent(byId);

                        model.setValueAt(byId.getId(), i, 0);
                        model.setValueAt(componentName.getText(), i, 1);
                        model.setValueAt(componentDescription.getText(), i, 2);
                        model.setValueAt(componentPrice.getText(), i, 3);

                        jDialog.dispose();

                    } else {
                        JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!! ");
                    }


                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(jDialog, "Wprowadź poprawnie wszystkie dane !!!");
                }
            }
        });
    }
}
