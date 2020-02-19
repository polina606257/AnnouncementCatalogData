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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public Email(String email) {
        this.email = email;
    }


    Email() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}

