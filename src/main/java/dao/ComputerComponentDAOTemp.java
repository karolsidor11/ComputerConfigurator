package dao;

import model.ComputerComponent;

import java.util.Map;

public interface ComputerComponentDAOTemp {

    Map<Integer, ComputerComponent> getAllComputerComponent();

    ComputerComponent getComputerComponent(Integer id);

    void updateComputerComponent(ComputerComponent computerComponent);

    void deleteComputerComponent(Integer id);

}
