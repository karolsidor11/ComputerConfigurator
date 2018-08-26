package entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Podzespoly")
public class ComputerComponent {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "Nazwa")
    private String componentName;
    @Column(name = "Opis")
    private String componentDescribe;
    @Column(name = "Cena")
    private BigDecimal price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
