package frame;

import panels.ComputerComponentPanel;
import panels.ComputerSetPanel;
import panels.CustomerPanel;
import panels.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JButton customerButton;
    private JButton componentButton;
    private JButton computerSetButton;
    private JButton aboutProgram;
    private JButton searchButton;
    private JLabel label;
    private JPanel panel;
    private ImageIcon imageIcon;
    private Font font;


    public MainFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        createFrame();
        createComponent();
        actionListner();
        setVisible(true);
    }

    private void createFrame() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {

        setSize(600, 580);
        setTitle("Computer Configurator");
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        SwingUtilities.updateComponentTreeUI(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/images/images.jpg")).getImage());
        setLocationByPlatform(true);
        setResizable(false);
        setLocationRelativeTo(null);


    }

    private void createComponent() {
        panel = new JPanel();
        customerButton = new JButton("Klienci");
        componentButton = new JButton("Podzespoły");
        computerSetButton = new JButton("Zamówienia");
        searchButton = new JButton("Wyszukaj");
        aboutProgram = new JButton("Info");
        label = new JLabel();
        imageIcon = new ImageIcon(MainFrame.class.getResource("/images/konfiguator.jpg"));
        label.setIcon(imageIcon);
        font = new Font(Font.DIALOG, Font.PLAIN, 12);

        customerButton.setFont(font);
        componentButton.setFont(font);
        computerSetButton.setFont(font);
        aboutProgram.setFont(font);
        searchButton.setFont(font);

        panel.setLayout(new BorderLayout());
        JPanel panels = new JPanel();
        panels.add(customerButton);
        panels.add(componentButton);
        panels.add(computerSetButton);
        panels.add(searchButton);

        JPanel about = new JPanel();
        about.add(aboutProgram);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel2.add(panels, BorderLayout.CENTER);
        panel2.add(about, BorderLayout.EAST);

        panel.add(panel2, BorderLayout.NORTH);
        panel.add(label, BorderLayout.CENTER);

        add(panel);

    }

    private void actionListner() {
        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(new CustomerPanel());
                pack();
            }
        });
        componentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setContentPane(new ComputerComponentPanel());
                pack();
            }
        });
        computerSetButton.addActionListener((e) -> {
            setContentPane(new ComputerSetPanel());
            pack();
        });
        aboutProgram.addActionListener((e ->
                JOptionPane.showMessageDialog(this, "     Computer Configurator \n Wersja demonstracyjna: v1.0" +
                        "\n Development by: Karol Sidor")));
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SearchPanel();
            }
        });
    }
}
