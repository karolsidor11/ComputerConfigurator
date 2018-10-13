package panels;

import daoimpl.ComputerSetJPA;
import frame.MainFrame;
import model.ComputerComponent;
import model.ComputerSet;
import service.Status;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ComputerSetPanel extends JPanel {
    //TODO nazwy zmiennych do poprawy
    private JButton addSet;
    private JButton deleteSet;
    private JButton update;
    private JButton back;
    private JToolBar toolBar;
    private JTable tableSet;
    private JScrollPane jScrollPane;
    private DefaultTableModel model;
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
        for (int i = 0; i < computerSets.size(); i++) {//TODO użyj foreach z Java 8
            Integer id = computerSets.get(i).getId();
            String computerSetName = computerSets.get(i).getComputerSetName();
            String computerSetDescribe = computerSets.get(i).getComputerSetDescribe();
            BigDecimal computerPrice = computerSets.get(i).getComputerPrice();
            model.Customer customer = computerSets.get(i).getCustomer();
            Object[] objects = {id, computerSetName, computerSetDescribe, computerPrice, customer};

            model.addRow(objects);
        }

        tableSet = new JTable(model);
        tableSet.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        tableSet.setBackground(Color.YELLOW);
        tableSet.setPreferredScrollableViewportSize(new Dimension(400, 350));
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
                AddComputerSetPanel addComputerSetPanel = new AddComputerSetPanel(model, Status.Add, "Panel dodawania zestawu komputerowego", tableSet);
                addComputerSetPanel.initValue();
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableSet.isRowSelected(tableSet.getSelectedRow())) {//TODO co podpowiada Intelij przy tym if ?
                    AddComputerSetPanel addComputerSetPanel = new AddComputerSetPanel(model, Status.Update, "Panel modyfikacji zestawu komputerowego", tableSet);
                    addComputerSetPanel.initUpdateValue();
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

    private void createPanelDeleteComputerSet() {

        int i = tableSet.getSelectedRow();
        if (i >= 0) {

            ComputerSetJPA computerSetJPA = new ComputerSetJPA();

            String valueAt = tableSet.getModel().getValueAt(i, 0).toString();
            ComputerSet setById = computerSetJPA.getSetById(Integer.parseInt(valueAt));
            computerSetJPA.removeComputerSet(setById);

            model.removeRow(i);
        } else {
            JOptionPane.showMessageDialog(this, "Wybierz zestaw komputerowy do usunięcia !");
        }
    }
}
