package daoHibernate;

import model.ComputerSet;

import java.util.List;

public interface ComputerSetDAO {

    ComputerSet getSetById(Integer id);

    List<ComputerSet> allList();

    void addComputerSet(ComputerSet computerSet);

    void mergeComputerSet(ComputerSet computerSet);

    void removeSet(ComputerSet computerSet);

    void removeSetById(Integer id);


}
