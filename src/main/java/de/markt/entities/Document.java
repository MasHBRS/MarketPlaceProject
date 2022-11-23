package de.markt.entities;

import java.io.Serializable;

import javax.persistence.*; 
 
import org.springframework.hateoas.RepresentationModel; 
import org.springframework.web.multipart.MultipartFile; 
 
import com.fasterxml.jackson.annotation.JsonFilter; 
import com.fasterxml.jackson.annotation.JsonIgnore; 
 
 
/** 
 * The persistent class for the M_DOCUMENTS database table. 
 *  
 */ 
@JsonFilter("FilterDocumentProps") 
@Entity 
@Table(name="M_DOCUMENTS") 
//@NamedQuery(name="MDocument.findAll", query="SELECT m FROM MDocument m") 
public class Document extends RepresentationModel<Document>  implements Serializable { 
        private static final long serialVersionUID = 1L; 
 
        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO) 
        private long id; 
 
        @Lob 
        private byte[] data; 
 
        private String name; 
 
        //bi-directional many-to-one association to MProduct 
        @ManyToOne 
        @JoinColumn(name = "PROCUDT_ID") 
        private Product Product; 
 
        public Document() { 
        } 
 
        public long getId() { 
                return this.id; 
        } 
 
        public void setId(long id) { 
                this.id = id; 
        } 
 
        public byte[] getData() { 
                return this.data; 
        } 
 
        public void setData(byte[] data) { 
                this.data = data; 
        } 
 
        public String getName() { 
                return this.name; 
        } 
 
        public void setName(String name) { 
                this.name = name; 
        } 
 
        public Product getProduct() { 
                return this.Product; 
        } 
 
        public void setProduct(Product Product) { 
                this.Product = Product; 
        } 
 
        public Document( byte[] data, String name,Product product) { 
                super(); 
                this.Product=product; 
                this.data = data; 
                this.name = name; 
        } 
}