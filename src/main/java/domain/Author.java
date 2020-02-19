package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private List<SuitableAd> suitableAds = new LinkedList<>();

    /**
     * Method adding ad to appropriate author
     * @param ad advertisement
     */
    public void addAds(Ad ad) {
        ads.add(ad);
    }


    public Author(String name, Phone phone, Address address, Email email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }


    public Author(int id, String name, Phone phone, Address address, Email email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }


    public Author() {}


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Phone getPhone() {
        return phone;
    }


    public void setPhone(Phone phone) {
        this.phone = phone;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public Email getEmail() {
        return email;
    }


    public void setEmail(Email email) {
        this.email = email;
    }


    public List<Ad> getAds() {
        return ads;
    }


    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }


    public List<SuitableAd> getSuitableAdsForAuthor() {
        return suitableAds;
    }


    public void setSuitableAdsForAuthor(List<SuitableAd> suitableAdsForAuthor) {
        this.suitableAds = suitableAdsForAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;

        Author author = (Author) o;

        return author.name.equals(name) && author.phone.getNumber().equals(phone.getNumber())
                && author.address.getCountry().equals(address.getCountry()) &&
                author.address.getCity().equals(address.getCity()) && author.email.getEmail().equals(email.getEmail());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}

