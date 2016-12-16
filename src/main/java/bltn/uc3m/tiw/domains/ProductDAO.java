package bltn.uc3m.tiw.domains;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product, Integer> {
	public <S extends Product> S save(Product product);
	public List<Product> findAll();
	public Product findByProductID(Integer productID);
	public void delete(Integer productID);
}
