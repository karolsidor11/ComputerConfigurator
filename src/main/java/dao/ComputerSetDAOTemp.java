package dao;

import model.ComputerSet;

import java.util.Map;

public interface ComputerSetDAOTemp {

    Map<Integer, ComputerSet> getAllComputerSet();

    ComputerSet getComputerSet(Integer id);

    void updateComputerSet(ComputerSet computerSet);

    void deleteComputerSet(Integer id);


}
