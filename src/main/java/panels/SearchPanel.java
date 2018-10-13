package panels;

import daoimpl.SearchJPA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SearchPanel extends JDialog {
    //TODO nazwy do poprawy
    private JTextArea jTextArea;
    private JButton searchAClient;
    private JButton searchComponent;
    private JButton searchAdres;
    private JButton searchComputerSet;
    private GridBagConstraints gridBagConstraints;
    private JLabel label;
    private JTextField jTextField;
    private JButton confirm;
    private JDialog jDialog;
    private Font font;
    private SearchJPA test;


    public SearchPanel() {
        createFrame();
        createComponents();
        addComponents();
        actionButons();
    }

    private void createFrame() {
        this.setTitle("Panel zaawansowanego wyszukiwania ");
        this.setSize(400, 300);
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(this);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void createComponents() {
        jTextArea = new JTextArea();
        searchAClient = new JButton("Wyszukaj klienta po imieniu");
        searchAdres = new JButton("Wyszukaj klientów po adresie");
        searchComponent = new JButton("Wyszukaj komponent po cenie");
        searchComputerSet = new JButton("Wyszukaj zestaw wg ceny");
        confirm = new JButton("Zatwierdź");
        label = new JLabel();
        jTextField = new JTextField();
        font = new Font("SansSerif", Font.PLAIN, 12);
        jTextField.setColumns(16);

        jTextArea.setColumns(20);
        jTextArea.setRows(12);
        jTextArea.setFont(font);
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

        JPanel jPanel = new JPanel(new GridBagLayout());

        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;


        jPanel.add(searchAClient, gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        jPanel.add(searchAdres, gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        jPanel.add(searchComponent, gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        jPanel.add(searchComputerSet, gridBagConstraints);

        JPanel jPanel1 = new JPanel(new GridBagLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        jPanel1.add(jTextArea, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(jPanel, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        this.add(jPanel1, gridBagConstraints);

        this.pack();
    }

    private void searchWindow() {
        jDialog = new JDialog();
        jDialog.setTitle("Okno wyszukiwania");
        jDialog.setSize(350, 100);
        jDialog.setResizable(false);
        jDialog.setLocationByPlatform(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setLayout(new FlowLayout());
        jDialog.add(label);
        jDialog.add(jTextField);
        jDialog.add(confirm);
        jDialog.setVisible(true);

    }

    //TODO Cała logika zapytań i entity manager powinny być w klasie DAO
    private void actionButons() {
        test = new SearchJPA(jTextField, jTextArea);

        searchAClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż  imię  klienta :  ");
                searchWindow();
                jTextArea.setText(null);
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        test.searchClient();
                        jDialog.dispose();
                    }
                });
            }
        });
        searchComputerSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż cenę zestawu  PC : ");
                searchWindow();
                jTextArea.setText(null);
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        test.searchComputerSet();
                        jDialog.dispose();
                    }
                });
            }
        });
        searchComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż cenę komponentu :");
                searchWindow();
                jTextArea.setText(null);
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        test.searchComponent();
                        jDialog.dispose();
                    }
                });
            }
        });
        searchAdres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż adres klienta : ");
                searchWindow();
                jTextArea.setText(null);
                confirm.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        test.searchAdres();
                        jDialog.dispose();
                    }
                });
            }
        });
    }
}
