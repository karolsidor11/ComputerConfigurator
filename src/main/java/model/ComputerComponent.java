package model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "Podzespoly")
public class ComputerComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "Nazwa")
    @NotBlank
    private String componentName;
    @Column(name = "Opis")
    @NotBlank
    private String componentDescribe;
    @Column(name = "Cena")
    @NotNull
    private BigDecimal price;

    public ComputerComponent() {
    }

    public ComputerComponent(@NotNull Integer id, @NotNull String componentName, @NotNull String componentDescribe, @NotNull BigDecimal price) {
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
