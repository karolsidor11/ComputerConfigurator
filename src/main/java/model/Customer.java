package model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Klienci")
public class Customer {

    @Id
    @GeneratedValue
    @NotNull
    @Min(value = 1)
    private Integer id;
    @Column(name = "Imie")
    @NotNull
    private String name;
    @Column(name = "Nazwisko")
    @NotNull
    private String lastname;
    @Column(name = "Adres")
    @NotNull
    private String adres;


    public Customer(@NotNull Integer id, @NotNull String name, @NotNull String lastname, @NotNull String adres) {
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
