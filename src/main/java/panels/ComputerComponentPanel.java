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
    private JButton back;
    private JToolBar toolBarButton;
    private JTable tableComponent;
    private JScrollPane jScrollPane;

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
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
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
        addComputerComponent = new JButton("Dodaj podzespół komputera");
        deleteComputerComponent = new JButton("Usuń podzespół komputera");
        back = new JButton("Wstecz");
        toolBarButton = new JToolBar();
        toolBarButton.add(back);
        toolBarButton.add(addComputerComponent);
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

                try {
                    MainFrame mainFrame = new MainFrame();
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e1) {
                    e1.printStackTrace();
                }

            }
        });
        deleteComputerComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog jDialog = new JDialog();
                JLabel jLabel = new JLabel();
                JTextField jTextField = new JTextField();
                JButton jButton = new JButton();
                jDialog.setLayout(new FlowLayout());
                jDialog.setLocationByPlatform(true);
                jDialog.setResizable(false);
                jDialog.setTitle("Usuwanie podzespołu");
                jLabel.setText("Wprowadź id podzespołu");
                jTextField.setPreferredSize(new Dimension(90, 20));
                jButton.setText("Usuń");
                jButton.setPreferredSize(new Dimension(90, 25));
                jDialog.add(jLabel);
                jDialog.add(jTextField);
                jDialog.add(jButton);
                jDialog.pack();
                jDialog.setVisible(true);


            }
        });

    }
}
