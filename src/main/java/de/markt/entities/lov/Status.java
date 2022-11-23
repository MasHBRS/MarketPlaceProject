package de.markt.entities.lov;
import java.io.Serializable; 
import java.util.List; 
 
import javax.persistence.*; 
import com.fasterxml.jackson.annotation.JsonFilter;

import de.markt.entities.Basket; 
 
/** 
 * The persistent class for the M_DOCUMENTS database table. 
 *  
 */ 
@JsonFilter("FilterStatusProps") 
@Entity 
@Table(name="M_STATUS") 
//@NamedQuery(name="MDocument.findAll", query="SELECT m FROM MDocument m") 
public class Status implements Serializable { 
        private static final long serialVersionUID = 1L; 
 
        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO) 
        private long id; 
 
        @Column(unique = true) 
        private String enName; 
        private String faName; 
        private String statusComment; 
        private String statusObject; 
        @OneToMany(mappedBy = "status")  
        private List<Basket> Baskets; 
         
        public Status() { 
        } 
 
        public long getId() { 
                return id; 
        } 
 
        public void setId(long id) { 
                this.id = id; 
        } 
 
        public String getEnName() { 
                return enName; 
        } 
 
        public void setEnName(String enName) { 
                this.enName = enName; 
        } 
 
        public String getFaName() { 
                return faName; 
        } 
 
        public void setFaName(String faName) { 
                this.faName = faName; 
        } 
 
        public String getStatusComment() { 
                return statusComment; 
        } 
 
        public void setStatusComment(String statusComment) { 
                this.statusComment = statusComment; 
        } 
 
        public String getStatusObject() { 
                return statusObject; 
        } 
 
        public void setStatusObject(String statusObject) { 
                this.statusObject = statusObject; 
        } 
 
        public List<Basket> getBaskets() { 
                return Baskets; 
        } 
 
        public void setBaskets(List<Basket> baskets) { 
                Baskets = baskets; 
        } 
}