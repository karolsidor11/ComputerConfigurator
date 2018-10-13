package dao;

import model.ComputerSet;

import java.util.List;

//TODO metody tutaj nazywają się raz Set a raz ComputerSet trzeba zdecydować jaka konwencja nazw
public interface ComputerSetDAO {

    ComputerSet getSetById(Integer id);

    List<ComputerSet> allList();

    void addComputerSet(ComputerSet computerSet);

    void mergeComputerSet(ComputerSet computerSet);

    void removeComputerSet(ComputerSet computerSet);

    void removeSetById(Integer id);


}
