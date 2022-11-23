package de.markt.entities;
import com.fasterxml.jackson.annotation.JsonFilter; 
import com.fasterxml.jackson.annotation.JsonIgnore; 
 
import java.io.Serializable; 
import javax.persistence.*; 
 
import org.springframework.hateoas.RepresentationModel; 
 
import java.util.List; 
/** 
 * The persistent class for the M_PRODUCT database table. 
 *  
 */ 
@JsonFilter("FilterProductProps") 
@Entity 
@Table(name="M_PRODUCT") 
//@NamedQuery(name="MProduct.findAll", query="SELECT m FROM MProduct m") 
public class Product  extends RepresentationModel<Product> implements Serializable { 
        private static final long serialVersionUID = 1L; 
 
        @Id 
        @GeneratedValue(strategy=GenerationType.AUTO) 
        private long id; 
 
        private String description; 
 
        private String name; 
 
        //bi-directional many-to-one association to MDocument 
        @OneToMany(mappedBy="Product") 
        private List<Document> Documents; 
 
        //bi-directional many-to-one association to MProductBasket 
        @OneToMany(mappedBy="Product") 
        private List<ProductBasket> ProductBaskets; 
         
        @OneToMany(mappedBy="product") 
        private List<OptionItem> optionItems; 
         
        /*@ManyToOne//(fetch = FetchType.LAZY) 
        @JoinColumn(name="Category_ID",nullable=true) 
        private Category category;*/ 
         
        @ManyToOne//(fetch = FetchType.LAZY) 
        @JoinColumn(name="CategoryBrand_ID",nullable=false) 
        private CategoryBrand categoryBrand; 
         
        public Product() { 
        } 
 
        public long getId() { 
                return this.id; 
        } 
 
        public void setId(long id) { 
                this.id = id; 
        } 
 
        public String getDescription() { 
                return this.description; 
        } 
 
        public void setDescription(String description) { 
                this.description = description; 
        } 
 
        public String getName() { 
                return this.name; 
        } 
 
        public void setName(String name) { 
                this.name = name; 
        } 
 
        public List<Document> getDocuments() { 
                return this.Documents; 
        } 
 
        public void setDocuments(List<Document> Documents) { 
                this.Documents = Documents; 
        } 
/* 
        public Document addDocument(Document Document) { 
                getDocuments().add(Document); 
                Document.setProduct(this); 
 
                return Document; 
        } 
 
        public Document removeDocument(Document Document) { 
                getDocuments().remove(Document); 
                Document.setProduct(null); 
 
                return Document; 
        } 
         
        public OprionProduct addOprionProduct(OprionProduct OprionProduct) { 
                getOprionProducts().add(OprionProduct); 
                OprionProduct.setProduct(this); 
 
                return OprionProduct; 
        } 
 
        public OprionProduct removeOprionProduct(OprionProduct OprionProduct) { 
                getOprionProducts().remove(OprionProduct); 
                OprionProduct.setProduct(null); 
 
                return OprionProduct; 
        } 
*/ 
        public List<ProductBasket> getProductBaskets() { 
                return this.ProductBaskets; 
        } 
 
        public void setProductBaskets(List<ProductBasket> ProductBaskets) { 
                this.ProductBaskets = ProductBaskets; 
        } 
 
        public CategoryBrand getCategoryBrand() { 
                return categoryBrand; 
        } 
 
        public void setCategoryBrand(CategoryBrand categoryBrand) { 
                this.categoryBrand = categoryBrand; 
        } 
 
        public List<OptionItem> getOptionItems() { 
                return optionItems; 
        } 
 
        public void setOptionItems(List<OptionItem> optionItems) { 
                this.optionItems = optionItems; 
        } 
/* 
        public ProductBasket addProductBasket(ProductBasket ProductBasket) { 
                getProductBaskets().add(ProductBasket); 
                ProductBasket.setProduct(this); 
 
                return ProductBasket; 
        } 
 
        public ProductBasket removeProductBasket(ProductBasket ProductBasket) { 
                getProductBaskets().remove(ProductBasket); 
                ProductBasket.setProduct(null); 
 
                return ProductBasket; 
        }*/ 
}