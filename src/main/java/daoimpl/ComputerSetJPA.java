package daoimpl;

import dao.ComputerSetDAO;
import model.ComputerSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ComputerSetJPA implements ComputerSetDAO {

    private static ComputerSetJPA instance;
    protected EntityManager entityManager;


    public ComputerSetJPA() {
        entityManager = getEntityManager();

    }

    private EntityManager getEntityManager() {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("configuratorPC");
        if (entityManager == null) {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }

    private ComputerSetJPA getInstance() {
        if (instance == null) {
            instance = new ComputerSetJPA();

        }
        return instance;

    }

    @Override
    public ComputerSet getSetById(Integer id) {
        return entityManager.find(ComputerSet.class, id);
    }

    @Override
    public List<ComputerSet> allList() {
        return entityManager.createQuery("FROM " + ComputerSet.class.getName()).getResultList();
    }

    @Override
    public void addComputerSet(ComputerSet computerSet) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(computerSet);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }

    @Override
    public void mergeComputerSet(ComputerSet computerSet) {

        try {

            entityManager.getTransaction().begin();
            entityManager.merge(computerSet);
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeComputerSet(ComputerSet computerSet) {

        try {

            entityManager.getTransaction().begin();
            ComputerSet computerSet1 = entityManager.find(ComputerSet.class, computerSet.getId());
            entityManager.remove(computerSet1);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }

    @Override
    public void removeSetById(Integer id) {

        try {
            ComputerSet computerSet = getSetById(id);
            entityManager.remove(computerSet);

        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }

    }
}
