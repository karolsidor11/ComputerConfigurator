package dao;

import model.ComputerComponent;

import java.util.List;
//TODO tak samo jak w ComputerSetDao do poprawy nazwy metod
public interface ComputerComponentDAO {

    ComputerComponent getById(Integer id);

    List<ComputerComponent> allComputerComponent();

    void addComputerComponent(ComputerComponent computerComponent);

    void mergeComponent(ComputerComponent computerComponent);

    void removeComputerComponent(ComputerComponent computerComponent);

    void removeComponentById(Integer id);


}
