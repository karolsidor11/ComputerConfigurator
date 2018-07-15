package model;

public class Customer {

    private Integer id;
    private String name;
    private String lastname;
    private String adres;


    public Customer(Integer id, String name, String lastname, String adres) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.adres = adres;
    }

    public Customer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
}
