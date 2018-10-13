package model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Zamówienia")
public class ComputerSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank
    @Column(name = "Nazwa")
    private String computerSetName;
    @Column(name = "Opis")
    @NotBlank
    private String computerSetDescribe;
    @Column(name = "Cena")
    @NotNull
    private BigDecimal computerPrice;

    @OneToOne
    @JoinColumn(name = "id_klienta")
    @Valid
    private Customer customer;

    @ManyToMany
    // Join Table (parametry)
    @Valid
    private List<ComputerComponent> computerComponentList = new ArrayList<ComputerComponent>();

    public ComputerSet() {
    }

    public ComputerSet(@NotNull Integer id, @NotNull String computerSetName, @NotNull String computerSetDescribe,
                       @NotNull BigDecimal computerPrice, @Valid Customer customer, @Valid List<ComputerComponent> computerComponentList) {
        this.id = id;
        this.computerSetName = computerSetName;
        this.computerSetDescribe = computerSetDescribe;
        this.computerPrice = computerPrice;
        this.customer = customer;
        this.computerComponentList = computerComponentList;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ComputerComponent> getComputerComponentList() {
        return computerComponentList;
    }

    public void setComputerComponentList(List<ComputerComponent> computerComponentList) {
        this.computerComponentList = computerComponentList;
    }
}
