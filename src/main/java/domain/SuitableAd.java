package domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="suitable_ad")
public class SuitableAd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String text;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "author_fk_id")
    @Valid
    @NotNull
    private Author author;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "category_fk_id")
    @Valid
    @NotNull
    private Category category;

    public SuitableAd(String text, BigDecimal priceFrom, BigDecimal priceTo) {
        this.text = text;
        this.priceFrom = priceFrom;
        this.priceTo = priceTo;
    }

    public SuitableAd(){}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
