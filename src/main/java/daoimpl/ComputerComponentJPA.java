package daoimpl;

import dao.ComputerComponentDAO;
import model.ComputerComponent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ComputerComponentJPA implements ComputerComponentDAO {


    private static ComputerComponentJPA instance;
    protected EntityManager entityManager;


    public ComputerComponentJPA() {
        entityManager = getEntityManager();

    }

    private static ComputerComponentJPA getInstance() {
        if (instance == null) {
            instance = new ComputerComponentJPA();
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

    @Override
    public ComputerComponent getById(Integer id) {

        return entityManager.find(ComputerComponent.class, id);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ComputerComponent> allComputerComponent() {

        return entityManager.createQuery("FROM " + ComputerComponent.class.getName()).getResultList();
    }

    @Override
    public void addComputerComponent(ComputerComponent computerComponent) {

        try {

            entityManager.getTransaction().begin();
            entityManager.persist(computerComponent);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void mergeComponent(ComputerComponent computerComponent) {

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(computerComponent);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void removeComputerComponent(ComputerComponent computerComponent) {

        try {

            ComputerComponent computerComponent1 = entityManager.find(ComputerComponent.class, computerComponent.getId());

            entityManager.getTransaction().begin();
            entityManager.remove(computerComponent1);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void removeComponentById(Integer id) {

        try {
            ComputerComponent computerComponent = getById(id);
            entityManager.remove(computerComponent);
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }
}
