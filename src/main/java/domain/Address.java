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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    /**
     * Address constructor . Creates Address object with parameters
     * @param country country
     * @param city city
     */
    public Address(Country country, City city) {
        this.country = country;
        this.city = city;
    }


    /**
     * Default address constructor. Creates Address object without parameters
     */
    Address() { }


    /**
     * Method getting address id
     * @return address id
     */
    public int getId() {
        return id;
    }


    /**
     * Method set address id
     * @param id address id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method getting country of address
     * @return country of address
     */
    public Country getCountry() {
        return country;
    }


    /**
     * Method sets country of address
     * @param country country
     */
    public void setCountry(Country country) {
        this.country = country;
    }


    /**
     * Method gets city of address
     * @return city of address
     */
    public City getCity() {
        return city;
    }


    /**
     * Method sets city of address
     * @param city city
     */
    public void setCity(City city) {
        this.city = city;
    }
}

