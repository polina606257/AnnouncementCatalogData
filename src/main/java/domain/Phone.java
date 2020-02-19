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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Phone(String number) {
        this.number = number;
    }


    Phone(){ }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }
}