package entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "Zamówienia")
public class ComputerSet {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "Nazwa")
    private String computerSetName;
    @Column(name = "Opis")
    private String computerSetDescribe;
    @Column(name = "Cena")
    private BigDecimal computerPrice;
    @Column(name = "Klient")
    private String customer;
    @Column(name = "Komponent")
    private String computerComponent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComputerSetName() {
        return computerSetName;
    }

    public void setComputerSetName(String computerSetName) {
        this.computerSetName = computerSetName;
    }

    public String getComputerSetDescribe() {
        return computerSetDescribe;
    }

    public void setComputerSetDescribe(String computerSetDescribe) {
        this.computerSetDescribe = computerSetDescribe;
    }

    public BigDecimal getComputerPrice() {
        return computerPrice;
    }

    public void setComputerPrice(BigDecimal computerPrice) {
        this.computerPrice = computerPrice;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getComputerComponent() {
        return computerComponent;
    }

    public void setComputerComponent(String computerComponentList) {
        this.computerComponent = computerComponentList;
    }
}
