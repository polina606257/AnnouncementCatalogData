package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class  Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @Size(min = 1, max = 30)
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "phone_fk_id")
    @Valid
    @NotNull
    private Phone phone;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "address_fk_id")
    @Valid
    @NotNull
    private Address address;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "email_fk_id")
    @Valid
    private domain.Email email;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "author",
            orphanRemoval = true)
    private List<Ad> ads = new LinkedList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY, mappedBy = "author",
            orphanRemoval = true)
    private List<Ad> suitableAds = new LinkedList<>();

//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinTable(name = "author_suitableAd", joinColumns = @JoinColumn(name = "author_fk_id"), inverseJoinColumns =
//    @JoinColumn(name = "suitableAd_fk_id"))
//    private List<SuitableAd> suitableAds = new ArrayList<>();

    public void addAds(Ad ad) {
        ads.add(ad);
    }

    public Author(String name, Phone phone, Address address, Email email) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    Author() {}

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

    public List<Ad> getSuitableAdsForAuthor() {
        return suitableAds;
    }

    public void setSuitableAdsForAuthor(List<Ad> suitableAdsForAuthor) {
        this.suitableAds = suitableAdsForAuthor;
    }
    }

