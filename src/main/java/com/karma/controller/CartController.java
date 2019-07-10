package com.karma.controller;

import com.karma.model.Cart;
import com.karma.model.User;
import com.karma.service.CartService;
import com.karma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CartController {

	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;

	@RequestMapping("/cart/list")
	public String list(Model model) {
		List<Cart> cartList = cartService.search("");
		model.addAttribute("cartList", cartList);

		return "admin/cart/cart-list";
	}

	@RequestMapping("/cart/add")
	public String add(Model model) {
		List<User> users = userService.search("");
		model.addAttribute("users", users);

		return "admin/cart/cart-add";
	}

	@PostMapping("/cart/add")
	public String add(Model model, @RequestParam("buyDate") Date date, @RequestParam("buyer") int idBuyer,
                      @RequestParam("seller") int idSeller) {
		Cart c = new Cart();
		c.setBuyDate(date);
		c.setBuyer(userService.get(idBuyer));
		c.setSeller(userService.get(idSeller));

		cartService.add(c);

		return "admin/cart/cart-list";
	}

	@GetMapping("/cart/delete/{id}")
	public String deleteCart(Model model, @PathVariable(value = "id") int id) {

		cartService.delete(id);
		return "redirect:/admin/cart/list";
	}

	@GetMapping("/cart/update/{id}")
	public String editCart(Model model, @PathVariable("id") int id) {

		Cart cart = cartService.get(id);
		model.addAttribute("cart", cart);
		List<User> users = userService.search("");
		model.addAttribute("users", users);

		return "admin/cart-update";
	}

	@PostMapping("/cart/update")
	public String editUser(Model model, @ModelAttribute Cart cart) throws IOException {
		cartService.update(cart);

		return "redirect:/admin/cart/list";
	}

}
