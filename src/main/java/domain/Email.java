package domain;

import javax.persistence.*;

/**
 * Class Email with parameters id, email, {@link Author} define author email
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
public class Email {

    /**
     * Field email id
     */
    @Column(name = "email_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Field email
     */
    @javax.validation.constraints.Email
    private String email;

    /**
     * Field author {@link Author}
     */
    @OneToOne(cascade = {CascadeType.PERSIST}, mappedBy = "email")
    private Author author;


    /**
     * Email constructor. Creates Email object with parameter email
     * @param email email
     */
    public Email(String email) {
        this.email = email;
    }


    /**
     * Default constructor. Creates Email object without parameters
     */
    Email() {
    }


    /**
     * Method gets email id
     * @return email id
     */
    public int getId() {
        return id;
    }


    /**
     * Method sets email id
     * @param id email id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method gets email
     * @return email
     */
    public String getEmail() {
        return email;
    }


    /**
     * Method sets email
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}

