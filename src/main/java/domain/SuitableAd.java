package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public SuitableAd(String text, BigDecimal priceFrom, BigDecimal priceTo, String category, Author author) {
        this.text = text;
        this.priceFrom = priceFrom.setScale(2, RoundingMode.HALF_UP);
        this.priceTo = priceTo.setScale(2, RoundingMode.HALF_UP);
        this.category = category;
        this.author = author;
    }


    public SuitableAd() {
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public BigDecimal getPriceFrom() {
        return priceFrom;
    }


    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }


    public BigDecimal getPriceTo() {
        return priceTo;
    }


    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }


    public Author getAuthor() {
        return author;
    }


    public void setAuthor(Author author) {
        this.author = author;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SuitableAd)) return false;

        SuitableAd suitableAd = (SuitableAd) o;

        return suitableAd.getText().equals(text) && suitableAd.getPriceFrom().equals(priceFrom) &&
                suitableAd.getPriceTo().equals(priceTo) && suitableAd.getCategory().equals(category);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + priceFrom.hashCode();
        result = 31 * result + priceTo.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}
