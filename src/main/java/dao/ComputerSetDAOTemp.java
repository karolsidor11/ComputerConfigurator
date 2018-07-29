package dao;

import model.ComputerSet;

import java.util.List;
import java.util.Map;

public interface ComputerSetDAOTemp {

    Map<Integer, ComputerSet> getAllComputerSet();

    List<ComputerSet> getAllComputerSets();

    ComputerSet getComputerSet(Integer id);

    void updateComputerSet(ComputerSet computerSet);

    void deleteComputerSet(Integer id);


}
