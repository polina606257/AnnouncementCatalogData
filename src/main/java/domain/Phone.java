package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

/**
 * Class Phone with parameters id, number, {@link Author} defines author phone
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
public class Phone {

    /**
     * Field phone id
     */
    @Column(name = "phone_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Field phone number
     */
    private String number;

    /**
     * Field author {@link Author}
     */
    @OneToOne(cascade = {CascadeType.PERSIST}, mappedBy = "phone")
    private Author author;


    /**
     * Phone constructor. Creates phone object with parameter number
     * @param number phone number
     */
    public Phone(String number) {
        this.number = number;
    }


    /**
     * Default phone constructor. Creates phone object without parameters
     */
    Phone(){ }


    /**
     * Method gets phone id
     * @return phone id
     */
    public int getId() {
        return id;
    }


    /**
     * Method sets phone id
     * @param id phone id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method get phone number
     * @return phone number
     */
    public String getNumber() {
        return number;
    }


    /**
     * Method sets phone number
     * @param number phone number
     */
    public void setNumber(String number) {
        this.number = number;
    }
}