package com.karma.dao;

import com.karma.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartDao extends JpaRepository<Cart, Integer> {
	@Query("Select c from Cart c")
	List<Cart> carts();

	@Query("Select c from Cart c")
	List<Cart> search();

	@Query("Select c from Cart c JOIN CartItem ci JOIN ci.product p WHERE p.name LIKE :name")
	List<Cart> searchByProduct(@Param("name") String name);

}
