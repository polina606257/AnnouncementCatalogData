package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

/**
 * Class Address with parameters id, {@link Country}, {@link City}, {@link Author} define address of author
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
public class Address {

    /**
     * Field address id
     */
    @Column(name = "address_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /**
     * Field country {@link Country}
     */
    @Enumerated(EnumType.STRING)
    private Country country;


    /**
     * Field city {@link City}
     */
    @Enumerated(EnumType.STRING)
    private City city;


    /**
     * Field author {@link Author}
     */
    @OneToOne(cascade = {CascadeType.PERSIST}, mappedBy = "address")
    private Author author;


    public Address(Country country, City city) {
        this.country = country;
        this.city = city;
    }


    Address() { }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Country getCountry() {
        return country;
    }


    public void setCountry(Country country) {
        this.country = country;
    }


    public City getCity() {
        return city;
    }


    public void setCity(City city) {
        this.city = city;
    }
}

