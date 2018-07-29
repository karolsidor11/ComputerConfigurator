package daoimpl;


import dao.ComputerComponentDAOTemp;
import model.ComputerComponent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputerComponentDAOImpl implements ComputerComponentDAOTemp {


    Map<Integer, ComputerComponent> componentMap = new HashMap<Integer, ComputerComponent>();
    List<ComputerComponent> computerComponents = new ArrayList<>();

    public ComputerComponentDAOImpl() {

        ComputerComponent procesor = new ComputerComponent
                (1, "Intel", "Core i5 ", new BigDecimal(1200));

        ComputerComponent mainboard = new ComputerComponent(2, "Giabyte",
                "MS900", new BigDecimal(800));
        ComputerComponent memory = new ComputerComponent(3, "Kingston",
                "HyperX", new BigDecimal(600));
        ComputerComponent disk = new ComputerComponent(4, "SSD", "Samsung",
                new BigDecimal(1500));

    }

    public Map<Integer, ComputerComponent> getAllComputerComponent() {
        return componentMap;
    }


    public List<ComputerComponent> getAllComputerComponents() {
        ComputerComponent procesor = new ComputerComponent
                (1, "Intel", "Core i5 ", new BigDecimal(1200));

        ComputerComponent mainboard = new ComputerComponent(2, "Giabyte",
                "MS900", new BigDecimal(800));
        ComputerComponent memory = new ComputerComponent(3, "Kingston",
                "HyperX", new BigDecimal(600));
        ComputerComponent disk = new ComputerComponent(4, "SSD", "Samsung",
                new BigDecimal(1500));
        computerComponents.add(procesor);
        computerComponents.add(mainboard);
        computerComponents.add(memory);
        computerComponents.add(disk);

        return computerComponents;
    }

    public ComputerComponent getComputerComponent(Integer id) {
        return componentMap.get(id);
    }

    public void updateComputerComponent(ComputerComponent computerComponent) {
        ComputerComponent old = componentMap.get(computerComponent.getId());
        old.setId(computerComponent.getId());
        old.setComponentName(computerComponent.getComponentName());
        old.setComponentDescribe(computerComponent.getComponentDescribe());
        old.setPrice(computerComponent.getPrice());
        componentMap.put(old.getId(), old);

    }

    public void deleteComputerComponent(Integer id) {
        componentMap.remove(id);
    }
}
