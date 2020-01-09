package domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SuitableAd.class)
public abstract class SuitableAd_ {

	public static volatile SingularAttribute<SuitableAd, BigDecimal> priceFrom;
	public static volatile SingularAttribute<SuitableAd, Author> author;
	public static volatile SingularAttribute<SuitableAd, Integer> id;
	public static volatile SingularAttribute<SuitableAd, String> text;
	public static volatile SingularAttribute<SuitableAd, BigDecimal> priceTo;
	public static volatile SingularAttribute<SuitableAd, String> category;

	public static final String PRICE_FROM = "priceFrom";
	public static final String AUTHOR = "author";
	public static final String ID = "id";
	public static final String TEXT = "text";
	public static final String PRICE_TO = "priceTo";
	public static final String CATEGORY = "category";

}

