package entity;

import javax.persistence.*;

@Entity
@Table(name = "Klienci")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    @Column(name = "Imie")
    private String name;
    @Column(name = "Nazwisko")
    private String lastname;
    @Column(name = "Adres")
    private String adres;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
