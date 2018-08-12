package panels;

import daoimpl.ComputerComponentDAOImpl;
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
    private JLabel insertId, insertComponentName, insertComponentDescription, insertPrice;
    private JTextField id, componentName, componentDescription, componentPrice;
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
        ComputerComponentDAOImpl dao = new ComputerComponentDAOImpl();
        List<ComputerComponent> allComputerComponent = dao.getAllComputerComponents();
        model = new DefaultTableModel(columnNames, 0);
        for (int i = 0; i < allComputerComponent.size(); i++) {
            Integer id = allComputerComponent.get(i).getId();
            String name = allComputerComponent.get(i).getComponentName();
            String componentDescribe = allComputerComponent.get(i).getComponentDescribe();
            BigDecimal price = allComputerComponent.get(i).getPrice();
            Object[] component = {id, name, componentDescribe, price};
            model.addRow(component);
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
            model.removeRow(i);
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz podzespół komputera do usunięcia !");
        }
    }

    private void createPanelAddComputerComponent() {

        jDialog = new JDialog();
        id = new JTextField();
        componentName = new JTextField();
        componentDescription = new JTextField();
        componentPrice = new JTextField();
        insertId = new JLabel("Wprowadź id produktu: ");
        insertComponentName = new JLabel("Wprowadź nazwę produktu: ");
        insertComponentDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        confirm = new JButton("Zatwierdź");

        componentName.setColumns(10);
        componentDescription.setColumns(10);
        componentPrice.setColumns(10);
        id.setColumns(10);


        jDialog.setTitle("Panel dodawania komponentu komputera");
        jDialog.setSize(320, 300);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());

        jDialog.add(insertId);
        jDialog.add(id);
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
                Object[] rows = new Object[4];
                rows[0] = id.getText();
                rows[1] = componentName.getText();
                rows[2] = componentDescription.getText();
                rows[3] = componentPrice.getText();

                model.addRow(rows);
                jDialog.dispose();

            }
        });
    }

    private void createPanelUpdateComputerComponent() {
        jDialog = new JDialog();
        id = new JTextField();
        componentName = new JTextField();
        componentDescription = new JTextField();
        componentPrice = new JTextField();
        insertId = new JLabel("Wprowadź id produktu: ");
        insertComponentName = new JLabel("Wprowadź nazwę produktu: ");
        insertComponentDescription = new JLabel("Wprowadź opis produktu: ");
        insertPrice = new JLabel("Wprowadź cenę produktu: ");
        confirm = new JButton("Zatwierdź");

        componentName.setColumns(10);
        componentDescription.setColumns(10);
        componentPrice.setColumns(10);
        id.setColumns(10);


        int a = tableComponent.getSelectedRow();
        id.setText(String.valueOf((Integer) model.getValueAt(a, 0)));
        componentName.setText((String) model.getValueAt(a, 1));
        componentDescription.setText((String) model.getValueAt(a, 2));
        componentPrice.setText(String.valueOf((BigDecimal) model.getValueAt(a, 3)));


        jDialog.setTitle("Panel modyfikacji komponentu komputera");
        jDialog.setSize(320, 300);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());

        jDialog.add(insertId);
        jDialog.add(id);
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

                model.setValueAt(id.getText(), i, 0);
                model.setValueAt(componentName.getText(), i, 1);
                model.setValueAt(componentDescription.getText(), i, 2);
                model.setValueAt(componentPrice.getText(), i, 3);

                jDialog.dispose();
            }
        });

    }
}
