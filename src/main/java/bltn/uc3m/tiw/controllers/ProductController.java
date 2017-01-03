package bltn.uc3m.tiw.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bltn.uc3m.tiw.domains.Product;
import bltn.uc3m.tiw.domains.ProductDAO;

@RestController
@CrossOrigin
public class ProductController {
	
	@Autowired
	ProductDAO productDao;
	
	@RequestMapping("/products/index")
	public List<Product> allProducts() {
		List<Product> products = productDao.findAll();
		return products;
	}
	
	@RequestMapping("/products/{id}/edit")
	public Product editProduct(@PathVariable("id") Integer id, @RequestBody Map<String, String[]> formParams) {
		try {
			Product product = productDao.findByProductID(id);
			Product editedProduct = editProductWithParams(formParams, product);
			Product savedProduct = ((CrudRepository<Product, Integer>)productDao).save(editedProduct);
			return savedProduct;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping("/products/{id}/delete")
	public boolean deleteProduct(@PathVariable("id") Integer id) {
		try {
			productDao.delete(id);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	@RequestMapping("/{id}/products/new")
	public Product newProduct(@RequestBody Map<String, String[]> formParams, @PathVariable("id") Integer id) {
		Product product = createProductFromParams(formParams);
		product.setUserID(id);
		
		try {
			Product savedProduct = ((CrudRepository<Product, Integer>)productDao).save(product);
			return savedProduct;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@RequestMapping("/products/{id}")
	public Product getProduct(@PathVariable("id") Integer id) {
		try {
			Product product = productDao.findByProductID(id);
			return product;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private Product createProductFromParams(Map<String, String[]> formParams) {
		// Extract values from params 
		String title = formParams.get("title")[0];
		String description = formParams.get("description")[0];
		String category = formParams.get("category")[0];
		Integer price = Integer.valueOf(formParams.get("price")[0]);
		
		Product initialisedProduct = new Product(title, description, category, null, 
					price, null);
		return initialisedProduct;
	}
	
	private Product editProductWithParams(Map<String, String[]> formParams, Product product) {
		// Extract values from params 
		String title = formParams.get("title")[0];
		String description = formParams.get("description")[0];
		String category = formParams.get("category")[0];
		String availability = formParams.get("availability")[0];
		Integer price = Integer.valueOf(formParams.get("price")[0]);

		product.setTitle(title);
		product.setDescription(description);
		product.setCategoryFromString(category);
		product.setAvailabilityFromString(availability);
		product.setPrice(price);

		return product;
	}

}
