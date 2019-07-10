package com.karma.dao;

import com.karma.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {
	@Query("Select c from CartItem c")
	List<CartItem> list();

	@Query("Select c from CartItem c JOIN c.product p where p.name like :name")
	List<CartItem> searchByProduct(@Param("name") String name);
}
