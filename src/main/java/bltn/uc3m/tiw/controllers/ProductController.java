package bltn.uc3m.tiw.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/{id}/products/new")
	public Product newProduct(@RequestBody Map<String, String[]> formParams, @PathVariable("id") Integer id) {
		Product product = createProductFromParams(formParams);
		product.setUserID(id);
		
		try {
			Product savedProduct = productDao.save(product);
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

}
