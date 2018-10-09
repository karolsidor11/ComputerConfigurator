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
    //TODO nazwy zmiennych do poprawy
    private JButton addComputerComponent;
    private JButton deleteComputerComponent;
    private JButton update;
    private JButton back;
    private JToolBar toolBarButton;
    private JTable tableComponent;
    private JScrollPane jScrollPane;
    private DefaultTableModel model;
    private Font font;


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
        for (ComputerComponent comp : computerComponents) {

            Integer id = comp.getId();
            String componentName = comp.getComponentName();
            String componentDescribe = comp.getComponentDescribe();
            BigDecimal price = comp.getPrice();

            Object[] objects = {id, componentName, componentDescribe, price};

            model.addRow(objects);
        }
        tableComponent = new JTable(model);
        tableComponent.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        tableComponent.setBackground(Color.YELLOW);
        tableComponent.setPreferredScrollableViewportSize(new Dimension(400, 350));
        jScrollPane = new JScrollPane(tableComponent);
        this.add(jScrollPane);
    }

    private void createToolBarButton() {
        addComputerComponent = new JButton("Dodaj ");
        deleteComputerComponent = new JButton("Usuń ");
        update = new JButton("Modyfikuj");
        back = new JButton("Wstecz");
        font = new Font(Font.DIALOG, Font.PLAIN, 12);
        addComputerComponent.setFont(font);
        deleteComputerComponent.setFont(font);
        update.setFont(font);
        back.setFont(font);
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

                AddComputerComponentPanel addComputerComponentPanel = new AddComputerComponentPanel
                        (model, "Add", tableComponent, "Panel dodawania komponentu komputera");
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
                    AddComputerComponentPanel addComputerComponentPanel = new AddComputerComponentPanel
                            (model, "Update", tableComponent, "Panel modyfikacji komponentu komputera");
                    addComputerComponentPanel.setComponentValue();
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
}
