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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class Ad with parameters id, name, date, text, price, active, {@link Author}, {@link Category} defines advertisement
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    /**
     * Field name is name of advertisements
     */
    @Size(min = 3, max = 20)
    private String name;


    /**
     * Field date is date when advertisement is creating
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    @JsonDeserialize(using = LocalDateDeserializator.class)
    @JsonSerialize(using = LocalDateSerializator.class)
    private LocalDate date;


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
    @NotNull
    private Author author;


    /**
     * Field category {@link Category}
     */
    @ManyToOne
    @JoinColumn(name = "category_fk_id")
    @Valid
    @NotNull
    private Category category;


    /**
     * Ad constructor. Creates Ad object with parameters
     * @param name ad name
     * @param date ad date
     * @param text ad text
     * @param price ad price
     * @param active active or inactive
     * @param author ad author
     * @param category ad category
     */
    public Ad(String name, LocalDate date, String text, BigDecimal price, boolean active, Author author, Category category) {
        this.name = name;
        this.date = date;
        this.text = text;
        this.price = price;
        this.active = active;
        this.author = author;
        this.category = category;
    }


    /**
     * Default constructor. Creates Ad object without any parameters
     */
    public Ad() {
    }


    /**
     * Method gets ad id
     * @return ad id
     */
    public int getId() {
        return id;
    }


    /**
     * Method sets ad id
     * @param id ad id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Method gets ad name
     * @return ad name
     */
    public String getName() {
        return name;
    }


    /**
     * Method sets ad name
     * @param name ad name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Method gets ad date
     * @return ad date
     */
    public LocalDate getDate() {
        return date;
    }


    /**
     * Method sets ad date
     * @param date ad date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }


    /**
     * Method gets ad text
     * @return ad text
     */
    public String getText() {
        return text;
    }


    /**
     * Method set ad text
     * @param text ad text
     */
    public void setText(String text) {
        this.text = text;
    }


    /**
     * Method gets ad price
     * @return price of product
     */
    public BigDecimal getPrice() {
        return price;
    }


    /** Method sets ad price
     * @param price ad price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    /**
     * Method gets ad author
     * @return ad author
     */
    public Author getAuthor() {
        return author;
    }


    /**
     * Method sets ad author
     * @param author ad author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }


    /**
     * Method gets ad category
     * @return ad category
     */
    public Category getCategory() {
        return category;
    }


    /**
     * Method sets ad category
     * @param category ad category
     */
    public void setCategory(Category category) {
        this.category = category;
    }


    /**
     * Method gets if ad is active or inActive
     * @return ad isActive or inActive
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Method sets if ad is active or inActive
     * @param active shows that ad is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }
}

