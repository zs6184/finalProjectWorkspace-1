package tw.springbootfinal.products.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products, Integer> {
	
	public List<Products> findByOnShelve(String status); 
}
