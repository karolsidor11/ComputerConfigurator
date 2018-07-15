package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ComputerSet {
    private Integer id;
    private String computerSetName;
    private String computerSetDescribe;
    private BigDecimal computerPrice;
    private Customer customer;
    private List<ComputerComponent> computerComponentList = new ArrayList<ComputerComponent>();

    public ComputerSet(Integer id, String computerSetName, String computerSetDescribe, BigDecimal computerPrice, Customer customer, List<ComputerComponent> computerComponentList) {
        this.id = id;
        this.computerSetName = computerSetName;
        this.computerSetDescribe = computerSetDescribe;
        this.computerPrice = computerPrice;
        this.customer = customer;
        this.computerComponentList = computerComponentList;
    }

    public ComputerSet() {
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
