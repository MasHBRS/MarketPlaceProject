package de.markt.entities;
import java.util.List; 

import javax.persistence.Column; 
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.JoinColumn; 
import javax.persistence.ManyToOne; 
import javax.persistence.OneToMany; 
import javax.persistence.OrderColumn; 
import javax.persistence.Table; 
 
import org.hibernate.annotations.CascadeType; 
 
import com.fasterxml.jackson.annotation.JsonFilter; 
import com.fasterxml.jackson.annotation.JsonIgnore; 
 
//@JsonFilter("FilterUserProps") 
@Entity 
@Table(name = "M_User") 
public class User { 
        @Id 
        @GeneratedValue(strategy = GenerationType.AUTO) 
        private long id; 
 
        @Column(nullable = false, unique = true) 
        private String username; 
 
        private String password; 
 
        @Column(unique = true) 
        private String email; 
 
        @JsonIgnore 
        @OneToMany()   
        @JoinColumn(name="user_id")   
        @OrderColumn(name="type")   
        private List<Basket> baskets;           
         
         
        @ManyToOne//(fetch = FetchType.LAZY) 
        @JoinColumn(name="Person_ID"/*,nullable=false*/) 
        private Person person ; 
         
        public long getId() { 
                return id; 
        } 
 
        public void setId(long id) { 
                this.id = id; 
        } 
 
        public String getUsername() { 
                return username; 
        } 
 
        public void setUsername(String username) { 
                this.username = username; 
        } 
 
        public String getPassword() { 
                return password; 
        } 
 
        public void setPassword(String password) { 
                this.password = password; 
        } 
 
        public String getEmail() { 
                return email; 
        } 
 
        public void setEmail(String email) { 
                this.email = email; 
        } 
 
        public User() { 
                super(); 
        } 
 
        public List<Basket> getBaskets() { 
                return baskets; 
        } 
 
        public void setBaskets(List<Basket> baskets) { 
                this.baskets = baskets; 
        } 
 
        public Person getPerson() { 
                return person; 
        } 
 
        public void setPerson(Person person) { 
                this.person = person; 
        } 
}