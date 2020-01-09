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
 * Class Category with  parameters id and category define category of advertisement
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
public class Category {

    /**
     * Field category id
     */
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Field name of category
     */
    @Column(unique = true)
    @Size(min = 3, max = 20)
    private String category;


    /**
     * List of ads related to each category
     */
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "category",
            orphanRemoval = true)
    private List<Ad> ads = new LinkedList<>();

    /**
     * Method adding ad to appropriate category
     * @param ad advertisement
     */
    public void addAd(Ad ad) {
        ads.add(ad);
    }


    /**
     * Constructor with parameter. Create category with certain name
     * @param category name of category
     */
    public Category(String category) {
        this.category = category;
    }


    /**
     * Default constructor. Create category without parameters
     */
    public Category() {
    }


    /**
     * Method getting id of category
     * @return category id
     */
    public int getId() {
        return id;
    }


    /**
     * Method set category id
     * @param id category id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method get category name
     * @return category
     */
    public String getCategory() {
        return category;
    }


    /**
     * Method set category name
     * @param category category name
     */
    public void setCategory(String category) {
        this.category = category;
    }


    /**
     * Method gets list ads for category
     * @return advertisements for category
     */
    public List<Ad> getAds() {
        return ads;
    }


    /**
     * Method set ads for category
     * @param ads advertisements
     */
    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
