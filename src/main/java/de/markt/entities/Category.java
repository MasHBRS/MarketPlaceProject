package de.markt.entities;

import java.io.Serializable;
import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

/**
 * The persistent class for the M_BASKET database table.
 * 
 */
@JsonFilter("FilterCategoryProps")
@Entity
@Table(name = "M_Category")
//@NamedQuery(name="MBasket.findAll", query="SELECT m FROM MBasket m") 
public class Category extends RepresentationModel<Category> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private Category parent;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Set<Category> children;

	/*
	 * @JsonIgnore
	 * 
	 * @OneToMany(mappedBy = "category") //@JsonProperty(access =
	 * JsonProperty.Access.WRITE_ONLY) private List<Product> products;
	 */
	@OneToMany(mappedBy = "category")
	private List<Option> options;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	private Set<CategoryBrand> categoryOfBrands;

	public Category() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<CategoryBrand> getCategoryOfBrands() {
		return categoryOfBrands;
	}

	public void setCategoryOfBrands(Set<CategoryBrand> categoryOfBrands) {
		this.categoryOfBrands = categoryOfBrands;
	}
}