package dao;

import model.ComputerComponent;

import java.util.List;

public interface ComputerComponentDAO {

    ComputerComponent getById(Integer id);

    List<ComputerComponent> allComputerComponent();

    void addComputerComponent(ComputerComponent computerComponent);

    void mergeComponent(ComputerComponent computerComponent);

    void removeComputerComponent(ComputerComponent computerComponent);

    void removeComponentById(Integer id);


}
