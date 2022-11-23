package de.markt.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

/**
 * The persistent class for the M_PRODUCT_BASKET database table.
 * 
 */

@JsonFilter("FilterProductBasketProps")
@Entity
@Table(name = "M_PRODUCT_BASKET")
//@NamedQuery(name="MProductBasket.findAll", query="SELECT m FROM MProductBasket m") 
public class ProductBasket extends RepresentationModel<ProductBasket> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private Long count;

	private String description;

	// bi-directional many-to-one association to MBasket
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BASKET_ID", nullable = true)
	private Basket Basket;

	// bi-directional many-to-one association to MProduct
	@ManyToOne
	@JoinColumn(name = "PROCUCT_ID")
	private Product Product;

	public ProductBasket() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Basket getBasket() {
		return this.Basket;
	}

	public void setBasket(Basket Basket) {
		this.Basket = Basket;
	}

	public Product getProduct() {
		return this.Product;
	}

	public void setProduct(Product Product) {
		this.Product = Product;
	}

	public ProductBasket(Long count, String description, Basket basket, Product product) {
		super();
		this.count = count;
		this.description = description;
		Basket = basket;
		Product = product;
	}
}