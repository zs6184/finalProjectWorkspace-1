package tw.springbootfinal.shoppingcart.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepostiory extends JpaRepository<ProductsBean, Integer> {

}
