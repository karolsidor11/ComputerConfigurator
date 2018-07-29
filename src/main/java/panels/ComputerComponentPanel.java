package panels;

import daoimpl.ComputerComponentDAOImpl;
import model.ComputerComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class ComputerComponentPanel extends JPanel {

    private JButton addComputerComponent;
    private JButton deleteComputerComponent;
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
        tableComponent= new JTable(model);
        tableComponent.setBackground(Color.YELLOW);
        tableComponent.setPreferredScrollableViewportSize(new Dimension(300,300));
        jScrollPane= new JScrollPane(tableComponent);
        this.add(jScrollPane);
    }

    private void createToolBarButton() {
        addComputerComponent = new JButton("Dodaj podzespół komputera");
        deleteComputerComponent = new JButton("Usuń podzespół komputera");
        toolBarButton = new JToolBar();
        toolBarButton.add(addComputerComponent);
        toolBarButton.add(deleteComputerComponent);
        this.add(toolBarButton);
    }

    private void createActionListner() {

    }
}
