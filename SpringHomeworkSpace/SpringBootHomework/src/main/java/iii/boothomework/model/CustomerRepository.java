package iii.boothomework.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
	//JPQL語法 等價於 from Customers where Username = ?1
	public Optional<Customers> findByUsername(String username);
}
