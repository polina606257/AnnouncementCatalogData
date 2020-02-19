package domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import deserializator.LocalDateDeserializator;
import serializator.AdSerializator;
import serializator.LocalDateSerializator;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class Ad with parameters id, name, localDate, text, price, active, {@link Author}, {@link Category} defines advertisement
 * @author Polina Shcherbinina
 * @version 1.1
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonSerialize(using = AdSerializator.class)
public class Ad {

    /**
     * Field id is advertisement id
     */
    @Column(name = "ad_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    /**
     * Field name is name of advertisements
     */
    @Size(min = 3, max = 20)
    private String name;


    /**
     * Field localDate is localDate when advertisement is creating
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
/*    @JsonDeserialize(using = LocalDateDeserializator.class)
    @JsonSerialize(using = LocalDateSerializator.class)*/
    private LocalDate localDate;


    /**
     * Field text is text of advertisement
     */
    @Size(min = 5, max = 100)
    private String text;


    /**
     * Field price is price of advertisement good
     */
    @Column(name = "price", precision = 10, scale = 2)
    private BigDecimal price;


    /**
     * Field active is showing if advertisement active or inactive
     */
    private boolean active;


    /**
     * Field author {@link Author}
     */
    @ManyToOne
    @JoinColumn(name = "author_fk_id")
    @Valid
//    @NotNull
    private Author author;


    /**
     * Field category {@link Category}
     */
    @ManyToOne
    @JoinColumn(name = "category_fk_id")
    @Valid
//    @NotNull
    private Category category;


    public Ad(String name, LocalDate localDate, String text, BigDecimal price,
              boolean active, Author author, Category category) {
        this.name = name;
        this.localDate = localDate;
        this.text = text;
        this.price = price;
        this.active = active;
        this.author = author;
        this.category = category;
    }

    public Ad() {

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


    public LocalDate getLocalDate() {
        return localDate;
    }


    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }


    public BigDecimal getPrice() {
        return price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
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


    public boolean isActive() {
        return active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ad)) return false;

        Ad ad = (Ad) o;

        return ad.name.equals(name) && ad.text.equals(text) && ad.price.compareTo(price) == 0 && ad.localDate.equals(localDate) &&
                ad.author.getId() == author.getId() && ad.category.getId() == category.getId();
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + category.hashCode();
        return result;
    }
}

