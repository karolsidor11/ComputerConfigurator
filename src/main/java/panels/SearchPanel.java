package panels;

import model.ComputerComponent;
import model.ComputerSet;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class SearchPanel extends JDialog {

    private JTextArea jTextArea;
    private JButton searchAClient;
    private JButton searchComponent;
    private JButton searchAdres;
    private JButton searchComputerSet;
    private GridBagConstraints gridBagConstraints;
    private JLabel label;
    private JTextField jTextField;
    private JButton confirm;
    private EntityManager entityManager;
    private JDialog jDialog;
    private Font font;


    public SearchPanel() {
        connectDataBase();
        createFrame();
        createComponents();
        addComponents();
        actionButons();
        searching();

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
        jTextArea.setRows(10);
        jTextArea.setFont(font);
    }

    private void addComponents() {
        this.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();

//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 0;
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
//        gridBagConstraints.anchor=GridBagConstraints.NORTH;
//
//
//        add(searchAClient, gridBagConstraints);
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy++;
//        add(searchComponent, gridBagConstraints);
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy++;
//        add(searchComputerSet, gridBagConstraints);
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy++;
//        add(searchAdres, gridBagConstraints);
//
////        gridBagConstraints.gridx = 0;
////        gridBagConstraints.gridy++;
//        gridBagConstraints.gridx++;
//        gridBagConstraints.gridy=0;
//        gridBagConstraints.fill = GridBagConstraints.BOTH;
//        add(jTextArea, gridBagConstraints);


        JPanel jPanel = new JPanel(new GridBagLayout());

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.insets= new Insets(4,4,4,4);
        gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;

        jPanel.add(searchAClient,gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx=0;
        jPanel.add(searchAdres,gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx=0;
        jPanel.add(searchComponent,gridBagConstraints);
        gridBagConstraints.gridy++;
        gridBagConstraints.gridx=0;
        jPanel.add(searchComputerSet,gridBagConstraints);

        JPanel jPanel1= new JPanel(new GridBagLayout());
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.fill=GridBagConstraints.BOTH;

        jPanel1.add(jTextArea, gridBagConstraints);

        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        this.add(jPanel,gridBagConstraints);
        gridBagConstraints.gridx=1;
        gridBagConstraints.gridy=0;
        this.add(jPanel1,gridBagConstraints);

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

    private void actionButons() {
        searchAClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż  imię  klienta :  ");
                searchWindow();
                EntityManager entityManager = connectDataBase();
            }
        });
        searchComputerSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż cenę zestawu  PC : ");
                searchWindow();
            }
        });
        searchComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż cenę komponentu :");
                searchWindow();
            }
        });
        searchAdres.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Wprowadż adres klienta : ");
                searchWindow();
            }
        });
    }

    private EntityManager connectDataBase() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }


    private void searching() {
        searchClient();
        searchComputerComponent();
        searchComputerSet();


    }

    private void searchClient() {
        searchAClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object = e.getSource();
                jTextArea.setText(null);

                if (object.equals(searchAClient)) {
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            entityManager.getTransaction().begin();
                            TypedQuery<Customer> query = entityManager.createQuery("SELECT e FROM Customer e WHERE e.name like:name", Customer.class);
                            query.setParameter("name", jTextField.getText());
                            List<Customer> resultList = query.getResultList();
                            for (Customer customer : resultList) {

                                String name = customer.getName();
                                String lastname = customer.getLastname();
                                jTextArea.insert(name + " " + lastname + "\n", 0);
                            }
                            entityManager.getTransaction().commit();
                            jTextField.setText(null);
                            jDialog.dispose();

                        }
                    });
                }
            }
        });
    }

    private void searchComputerComponent() {

        searchComponent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object object = e.getSource();
                jTextArea.setText(null);

                if (object.equals(searchComponent)) {
                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("TOOOOO");

                            entityManager.getTransaction().begin();

                            TypedQuery<ComputerComponent> query = entityManager.createQuery(
                                    "SELECT e FROM ComputerComponent e WHERE e.price>:cena", ComputerComponent.class);

                            query.setParameter("cena", BigDecimal.valueOf(Double.parseDouble(jTextField.getText())));
                            List<ComputerComponent> resultList = query.getResultList();

                            for (ComputerComponent comp : resultList) {

                                String componentName = comp.getComponentName();
                                BigDecimal price = comp.getPrice();

                                jTextArea.insert(componentName + "-" + price + "\n", 0);
                            }
                            jDialog.dispose();
                            jTextField.setText(null);
                            entityManager.getTransaction().commit();
                        }
                    });
                }
            }
        });
    }

    private void searchComputerSet() {

        searchComputerSet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Object object = e.getSource();
                jTextArea.setText(null);

                if (object.equals(searchComputerSet)) {

                    confirm.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {


                            entityManager.getTransaction().begin();

                            TypedQuery<ComputerSet> query = entityManager.createQuery
                                    ("SELECT e FROM ComputerSet e WHERE e.computerPrice>:price", ComputerSet.class);

                            query.setParameter("price", BigDecimal.valueOf(Double.parseDouble(jTextField.getText())));

                            List<ComputerSet> resultList = query.getResultList();

                            for (ComputerSet compSet : resultList) {

                                String computerSetName = compSet.getComputerSetName();
                                String computerSetDescribe = compSet.getComputerSetDescribe();
                                BigDecimal computerPrice = compSet.getComputerPrice();

                                jTextArea.insert(computerSetName + " " + computerSetDescribe + " " + computerPrice + "\n", 0);
                            }

                            entityManager.getTransaction().commit();
                            jDialog.dispose();
                            jTextField.setText(null);
                        }

                    });
                }
            }
        });
    }

}
