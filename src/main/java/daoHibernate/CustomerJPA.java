package daoHibernate;

import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class CustomerJPA implements CustomerDAO {

    private static CustomerJPA instance;
    protected EntityManager entityManager;

    public CustomerJPA() {
        entityManager = getEntityManager();

    }

    private static CustomerJPA getInstance() {
        if (instance == null) {
            instance = new CustomerJPA();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");

        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();

        }
        return entityManager;

    }

    public Customer getById(Integer id) {
        return entityManager.find(Customer.class, id);

    }

    @SuppressWarnings("unchecked")
    public List<Customer> allCustomer() {

        return entityManager.createQuery("FROM " + Customer.class.getName()).getResultList();

    }

    public void addCustomer(Customer customer) {

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    public void mergeCustomer(Customer customer) {

        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    public void removeCustomer(Customer customer) {

        try {
            entityManager.getTransaction().begin();
            Customer customer1 = entityManager.find(Customer.class, customer.getId());
            entityManager.remove(customer1);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    public void removeById(Integer id) {

        try {
            Customer customer = getById(id);
            removeCustomer(customer);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
