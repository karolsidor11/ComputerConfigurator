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

//        addCustomers();
//        addComputerComponent();
//        addComputerSet();
    }

    public void addCustomers() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        CustomerDAOTempImpl customerDAOTemp = new CustomerDAOTempImpl();
        List<Customer> allCustomerList = customerDAOTemp.getAllCustomerList();

        for (Customer customers : allCustomerList) {
            Customer customer = new Customer();
            customer.setName(customers.getName());
            customer.setLastname(customers.getLastname());
            customer.setAdres(customers.getAdres());

            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    public void addComputerComponent() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ComputerComponentDAOImpl computerComponentDAO = new ComputerComponentDAOImpl();
        List<ComputerComponent> allComputerComponents = computerComponentDAO.getAllComputerComponents();

        for (ComputerComponent computerComponent : allComputerComponents) {

            ComputerComponent computerComponent1 = new ComputerComponent();
            computerComponent1.setComponentName(computerComponent.getComponentName());
            computerComponent1.setComponentDescribe(computerComponent.getComponentDescribe());
            computerComponent1.setPrice(computerComponent.getPrice());

            entityManager.getTransaction().begin();
            entityManager.persist(computerComponent1);
            entityManager.getTransaction().commit();
        }
        entityManager.close();
        entityManagerFactory.close();
    }

    public void addComputerSet() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ComputerSetDAOImpl computerSetDAO = new ComputerSetDAOImpl();
        List<ComputerSet> allComputerSets = computerSetDAO.getAllComputerSets();


        for (ComputerSet computer : allComputerSets) {

            ComputerSet computerSet = new ComputerSet();
            computerSet.setComputerSetName(computer.getComputerSetName());
            computerSet.setComputerSetDescribe(computer.getComputerSetDescribe());
            computerSet.setComputerPrice(computer.getComputerPrice());
            computerSet.setCustomer(computer.getCustomer());
            computerSet.setComputerComponentList(computer.getComputerComponentList());


            entityManager.getTransaction().begin();

            entityManager.persist(computerSet);
            entityManager.getTransaction().commit();
        }


        entityManager.close();
        entityManagerFactory.close();
    }

    public void initData() {
        Main main = new Main();
        main.addCustomers();
        main.addComputerComponent();
        main.addComputerSet();
    }

}


