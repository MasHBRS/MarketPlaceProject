package de.markt.entities;

import javax.persistence.*;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the M_BASKET database table.
 * 
 */
@JsonFilter("FilterPersonProps")
@Entity
@Table(name = "M_PERSON")
//@NamedQuery(name="MBasket.findAll", query="SELECT m FROM MBasket m") 
public class Person extends RepresentationModel<Person> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String family;

	// bi-directional many-to-one association to MProductBasket
	@OneToMany(mappedBy = "person") // This "Basket" is the reference to the property in ProductBasket not this
									// Class.
	private List<User> users;

	public Person() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}