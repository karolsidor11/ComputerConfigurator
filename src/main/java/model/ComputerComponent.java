package model;



import java.math.BigDecimal;


public class ComputerComponent {
    private Integer id;
    private String componentName;
    private String componentDescribe;
    private BigDecimal price;


    public ComputerComponent() {
    }

    public ComputerComponent(Integer id, String componentName, String componentDescribe, BigDecimal price) {
        this.id = id;
        this.componentName = componentName;
        this.componentDescribe = componentDescribe;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentDescribe() {
        return componentDescribe;
    }

    public void setComponentDescribe(String componentDescribe) {
        this.componentDescribe = componentDescribe;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
