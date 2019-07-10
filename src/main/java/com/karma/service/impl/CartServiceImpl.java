package com.karma.service.impl;

import com.karma.dao.CartDao;
import com.karma.model.Cart;
import com.karma.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao cartDao;

	@Override
	public void add(Cart cart) {
		cartDao.save(cart);
	}

	@Override
	public void update(Cart cart) {
		Cart currentCart = get(cart.getId());
		if (currentCart != null) {
			currentCart.setBuyDate(cart.getBuyDate());
			currentCart.setBuyer(cart.getBuyer());
			currentCart.setSeller(cart.getSeller());
			currentCart.setCartItems(cart.getCartItems());

			cartDao.save(currentCart);
		}

	}

	@Override
	public Cart get(int id) {
		return cartDao.findById(id).orElse(null);
	}

	@Override
	public void delete(int id) {
		cartDao.deleteById(id);
	}

	@Override
	public List<Cart> search(String name) {
		List<Cart> carts = cartDao.search();
		return carts;
	}

	@Override
	public List<Cart> searchByProduct(String name) {

		return cartDao.searchByProduct(name);
	}

}
