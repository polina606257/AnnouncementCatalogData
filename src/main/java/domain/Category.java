package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

/**
 * Class Category with  parameters id and name define name of advertisement
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
public class Category {

    /**
     * Field name id
     */
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Field name of name
     */
    @Column(unique = true)
    @Size(min = 3, max = 20)
    private String name;


    /**
     * List of ads related to each name
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "name",
            orphanRemoval = true)
    private List<Ad> ads = new LinkedList<>();

    /**
     * Method adding ad to appropriate name
     * @param ad advertisement
     */
    public void addAd(Ad ad) {
        ads.add(ad);
    }


    public Category(String name) {
        this.name = name;
    }


    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public Category() {
    }


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


    public List<Ad> getAds() {
        return ads;
    }


    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
