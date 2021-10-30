package tw.springbootfinal.products.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepo extends JpaRepository<Products, Integer> {

}
