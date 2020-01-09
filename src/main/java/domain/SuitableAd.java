package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Class SuitableAd defines specific ad with certain parameters that authors would want to be informed when new ad with
 * appropriate parameters are appeared
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "suitable_ad")
public class SuitableAd {

    /**
     * Field suitableAd id
     */
    @Column(name = "suitable_ad_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Field text is text we want ad name {@link Ad#name} would contain
     */
    private String text;

    /**
     * Field priceFrom is ad price {@link Ad#price} can not be lower then priceFrom
     */
    private BigDecimal priceFrom;

    /**
     * Field priceTo is ad price {@link Ad#price} can not be higher then priceTo
     */
    private BigDecimal priceTo;

    /**
     * Field category is name of category of advertisements we want to see
     */
    private String category;

    /**
     * Field author {@link Author}
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_fk_id")
    @Valid
    @NotNull
    private Author author;


    /**
     * SuitableAd constructor. Created SuitableAd object with parameters
     * @param text suitableAd text
     * @param priceFrom priceFrom
     * @param priceTo priceTo
     * @param category name of category
     */
    public SuitableAd(String text, BigDecimal priceFrom, BigDecimal priceTo, String category) {
        this.text = text;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
        this.category = category;
    }


    /**
     * Default constructor. Creates SuitableAd without parameters
     */
    public SuitableAd() {
    }


    /**
     * Method gets suitable ad id
     * @return suitable ad id
     */
    public int getId() {
        return id;
    }


    /**
     * Method sets suitable ad id
     * @param id suitable ad id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method gets suitable ad text
     * @return suitable ad text
     */
    public String getText() {
        return text;
    }


    /**
     * Method sets suitable ad text
     * @param text suitable ad text
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * Method gets priceFrom
     * @return priceFrom
     */
    public BigDecimal getPriceFrom() {
        return priceFrom;
    }


    /**
     * Method sets priceFrom
     * @param priceFrom priceFRom
     */
    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }


    /**
     * Method gets priceTo
     * @return priceTo
     */
    public BigDecimal getPriceTo() {
        return priceTo;
    }


    /**
     * Method sets priceTo
     * @param priceTo priceTo
     */
    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }


    /**
     * Method gets suitable ad author
     * @return suitable ad author
     */
    public Author getAuthor() {
        return author;
    }


    /**
     * Method sets suitable ad author
     * @param author suitable ad author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }


    /**
     * Method gets category name
     * @return category name
     */
    public String getCategory() {
        return category;
    }


    /**
     * Method sets category name
     * @param category category name
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
