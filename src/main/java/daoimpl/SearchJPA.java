package daoimpl;

import dao.SearchDAO;
import model.Adres;
import model.ComputerComponent;
import model.ComputerSet;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public class SearchJPA implements SearchDAO {

    private EntityManager entityManager;
    private JTextField jTextField;
    private JTextArea jTextArea;

    public SearchJPA(JTextField jTextField, JTextArea jTextArea) {

        this.jTextArea = jTextArea;
        this.jTextField = jTextField;

        getInstance();
    }

    private EntityManager getInstance() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }


    @Override
    public void searchClient() {

        if (jTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Wprowadź dane !!!");
        } else {

            TypedQuery<Customer> query = entityManager.createQuery("SELECT e FROM Customer e WHERE e.name like:name", Customer.class);
            query.setParameter("name", jTextField.getText());
            List<Customer> resultList = query.getResultList();
            for (Customer customer : resultList) {

                String fristName = customer.getName();
                String lastname = customer.getLastname();
                Adres adres = customer.getAdres();
                String locality = adres.getLocality();

                jTextArea.insert(fristName + " " + lastname + " " + locality + "\n", 0);
            }
            jTextField.setText(null);
        }
    }

    @Override
    public void searchComponent() {
        if (jTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Wprowadź dane !!!");
        } else {

            TypedQuery<ComputerComponent> query = entityManager.createQuery(
                    "SELECT e FROM ComputerComponent e WHERE e.price<:cena", ComputerComponent.class);

            query.setParameter("cena", BigDecimal.valueOf(Double.parseDouble(jTextField.getText())));
            List<ComputerComponent> resultList = query.getResultList();

            for (ComputerComponent comp : resultList) {

                String componentName = comp.getComponentName();
                BigDecimal price = comp.getPrice();

                jTextArea.insert(componentName + "-" + price + "zł" + "\n", 0);

            }
            jTextField.setText(null);
        }
    }

    @Override
    public void searchComputerSet() {

        if (jTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Wprowadź dane !!!");
        } else {

            TypedQuery<ComputerSet> query = entityManager.createQuery
                    ("SELECT e FROM ComputerSet e WHERE e.computerPrice>:price", ComputerSet.class);

            query.setParameter("price", BigDecimal.valueOf(Double.parseDouble(jTextField.getText())));

            List<ComputerSet> resultList = query.getResultList();

            for (ComputerSet compSet : resultList) {

                String computerSetName = compSet.getComputerSetName();
                String computerSetDescribe = compSet.getComputerSetDescribe();
                BigDecimal computerPrice = compSet.getComputerPrice();

                jTextArea.insert(computerSetName + " - " + computerSetDescribe + " - " + computerPrice + " zł" + "\n", 0);
            }
            jTextField.setText(null);
        }
    }

    @Override
    public void searchAdres() {

        if (jTextField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Wprowadź dane !!!");
        } else {
            TypedQuery<Customer> query = entityManager.createQuery("SELECT e FROM Customer e", Customer.class);

            List<Customer> resultList = query.getResultList();

            for (Customer customer : resultList) {
                Adres adres = customer.getAdres();

                String locality = adres.getLocality();
                if (locality.equals(jTextField.getText())) {

                    String name = customer.getName();
                    String lastname = customer.getLastname();
                    String locality1 = adres.getLocality();

                    jTextArea.insert(name + " " + lastname + " " + locality1 + "\n", 0);
                }
            }
            jTextField.setText(null);
        }
    }
}
