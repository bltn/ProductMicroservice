package bltn.uc3m.tiw.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Entity
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="product_id")
	private Integer productID;
	
	@Size(min = 2, max = 40)
	private String title;
	@Size(min  = 2, max = 400)
	private String description;
	@Enumerated(EnumType.STRING)
	private Category category;
	@Lob
	private byte[] photo;
	private Integer price;
	@Enumerated(EnumType.STRING)
	private Availability availability; 
	@Column(name="user_id")
	private Integer userID;
	
	public Product() {}
	
	public Product(String title, String description, String category,
			byte[] photo, Integer price, Integer userID) {
		
		this.productID = null;
		this.title = title;
		this.description = description;
		this.category = Category.valueOf(category);
		this.photo = photo;
		this.price = price;
		this.availability = Availability.AVAILABLE;
		this.userID = userID;
	}
	
	public Integer getProductID() {
		return productID;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public int getPrice() {
		return price;
	}
	
	public Category getCategory() {
		return category;
	}

	public Availability getAvailability() {
		return availability;
	}

	public int getUserID() {
		return userID;
	}

	public void setProductID(Integer productID) {
		this.productID = productID;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setAvailability(Availability availability) {
		this.availability = availability;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public static enum Category {
		BEDROOM, KITCHEN, OFFICE, BATHROOM, OTHER;
	}
	
	public static enum Availability {
		AVAILABLE, RESERVED, SOLD;
	}
}
