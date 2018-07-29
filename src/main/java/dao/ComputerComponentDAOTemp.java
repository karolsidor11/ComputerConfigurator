package dao;

import model.ComputerComponent;

import java.util.List;
import java.util.Map;

public interface ComputerComponentDAOTemp {

    Map<Integer, ComputerComponent> getAllComputerComponent();

    List<ComputerComponent> getAllComputerComponents();

    ComputerComponent getComputerComponent(Integer id);

    void updateComputerComponent(ComputerComponent computerComponent);

    void deleteComputerComponent(Integer id);

}
