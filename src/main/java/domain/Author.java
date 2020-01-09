package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Author with parameters id, name, {@link Phone}, {@link Address}, {@link Email}, ads {@link Ad}, suitableAds
 * {@link SuitableAd} define author of advertisement
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class  Author {

    /**
     * Field author id
     */
    @Column(name = "author_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    /**
     * Field author name
     */
    @NotNull
    @Size(min = 1, max = 30)
    private String name;


    /**
     * Field author phone {@link Phone}
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "phone_fk_id")
    @Valid
    @NotNull
    private Phone phone;


    /**
     * Field author address {@link Address}
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "address_fk_id")
    @Valid
    @NotNull
    private Address address;


    /**
     * Field author email {@link Email}
     */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "email_fk_id")
    @Valid
    private domain.Email email;


    /**
     * Field ads is ads related to appropriate author {@link Ad}
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "author",
            orphanRemoval = true)
    private List<Ad> ads = new LinkedList<>();


    /**
     * Field suitableAds - suitableAds related to appropriate author {@link SuitableAd}
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "author",
            orphanRemoval = true)
    private List<Ad> suitableAds = new LinkedList<>();

    /**
     * Method adding ad to appropriate author
     * @param ad advertisement
     */
    public void addAds(Ad ad) {
        ads.add(ad);
    }


    /** Author constructor . Creates Author object with certain parameters
     * @param name author name
     * @param phone author phone {@link Phone}
     * @param address author address {@link Address}
     * @param email author email {@link Email}
     */
    public Author(String name, Phone phone, Address address, Email email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }


    /**
     * Default author constructor. Creates Author without parameters
     */
    Author() {}


    /**
     * Method gets author id
     * @return author id
     */
    public int getId() {
        return id;
    }


    /**
     * Method sets author id
     * @param id author id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method gets author name
     * @return author name
     */
    public String getName() {
        return name;
    }


    /**
     * Method sets author name
     * @param name author name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Method gets author phone
     * @return author phone
     */
    public Phone getPhone() {
        return phone;
    }


    /**
     * Method sets author phone
     * @param phone author phone
     */
    public void setPhone(Phone phone) {
        this.phone = phone;
    }


    /**
     * Method gets author address
     * @return author address
     */
    public Address getAddress() {
        return address;
    }


    /**
     * Methods sets author address
     * @param address author address
     */
    public void setAddress(Address address) {
        this.address = address;
    }


    /**
     * Method gets author email
     * @return author email
     */
    public Email getEmail() {
        return email;
    }


    /**
     * Method sets author email
     * @param email author email
     */
    public void setEmail(Email email) {
        this.email = email;
    }


    /**
     * Method gets list of advertisements related to author
     * @return advertisements related to author
     */
    public List<Ad> getAds() {
        return ads;
    }


    /**
     * Method sets list of ads related to author
     * @param ads ads related to author
     */
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }


    /**
     * Method gets kist of suitable advertisemnts related to author
     * @return suitable ads related to author
     */
    public List<Ad> getSuitableAdsForAuthor() {
        return suitableAds;
    }


    /**
     * Method sets list of suitable ads related to author
     * @param suitableAdsForAuthor suitable advertisements related to author
     */
    public void setSuitableAdsForAuthor(List<Ad> suitableAdsForAuthor) {
        this.suitableAds = suitableAdsForAuthor;
    }
    }

