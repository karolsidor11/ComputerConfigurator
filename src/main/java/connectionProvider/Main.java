package connectionProvider;

import daoimpl.ComputerComponentDAOImpl;
import daoimpl.ComputerSetDAOImpl;
import daoimpl.CustomerDAOTempImpl;
import model.ComputerComponent;
import model.ComputerSet;
import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CustomerDAOTempImpl customerDAOTemp = new CustomerDAOTempImpl();
        List<Customer> allCustomerList = customerDAOTemp.getAllCustomerList();

        for (Customer customers : allCustomerList) {
            entity.Customer customer = new entity.Customer();
            customer.setName(customers.getName());
            customer.setLastname(customers.getLastname());
            customer.setAdres(customers.getAdres());

            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        }

        ComputerComponentDAOImpl computerComponentDAO = new ComputerComponentDAOImpl();
        List<ComputerComponent> allComputerComponents = computerComponentDAO.getAllComputerComponents();
        for (ComputerComponent components : allComputerComponents) {
            entity.ComputerComponent computerComponent = new entity.ComputerComponent();
            computerComponent.setComponentName(components.getComponentName());
            computerComponent.setComponentDescribe(components.getComponentDescribe());
            computerComponent.setPrice(components.getPrice());
            entityManager.getTransaction().begin();
            entityManager.persist(computerComponent);
            entityManager.getTransaction().commit();
        }

        ComputerSetDAOImpl computerSetDAO = new ComputerSetDAOImpl();
        List<ComputerSet> allComputerSets = computerSetDAO.getAllComputerSets();


        for (ComputerSet set : allComputerSets) {
            entity.ComputerSet computerSet = new entity.ComputerSet();
            computerSet.setComputerSetName(set.getComputerSetName());
            computerSet.setComputerSetDescribe(set.getComputerSetDescribe());
            computerSet.setComputerPrice(set.getComputerPrice());
            computerSet.setCustomer(set.getCustomer().getName());

            // Tutaj nie wiem jak  dodac do bazy danych komponent który jest przechowywany w liście


            entityManager.getTransaction().begin();
            entityManager.persist(computerSet);
            entityManager.getTransaction().commit();

        }


        entityManager.close();
        entityManagerFactory.close();
    }
}
