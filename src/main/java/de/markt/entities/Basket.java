package de.markt.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;

import de.markt.entities.lov.Status;

import java.util.List;

/**
 * The persistent class for the M_BASKET database table.
 * 
 */
@JsonFilter("FilterBasketProps")
@Entity
@Table(name = "M_BASKET")
//@NamedQuery(name="MBasket.findAll", query="SELECT m FROM MBasket m") 
public class Basket extends RepresentationModel<Basket> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String code;

	private String name;

	@Column(name = "USER_ID", nullable = false)
	private long userID;
	// bi-directional many-to-one association to MProductBasket
	@OneToMany(mappedBy = "Basket") // This "Basket" is the reference to the property in ProductBasket not this
									// Class.
	private List<ProductBasket> ProductBaskets;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "STATUS_ID", nullable = false)
	private Status status;

	public Basket() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProductBasket> getProductBaskets() {
		return this.ProductBaskets;
	}

	public void setProductBaskets(List<ProductBasket> ProductBaskets) {
		this.ProductBaskets = ProductBaskets;
	}

	public long getUserID() {
		return userID;
	}

	public void setUserID(long userID) {
		this.userID = userID;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
