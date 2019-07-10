package com.karma.controller;

import com.karma.model.Cart;
import com.karma.model.CartItem;
import com.karma.model.Product;
import com.karma.service.CartItemService;
import com.karma.service.CartService;
import com.karma.service.ProductService;
import com.karma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CartItemController {

	@Autowired
	UserService userService;
	@Autowired
	CartService cartService;
	@Autowired
	CartItemService cartItemService;
	@Autowired
	ProductService productService;

	// list
	@RequestMapping("/cart-item/list")
	public String list(Model model) {
		List<CartItem> cartItemList = cartItemService.search("");
		model.addAttribute("cartItemList", cartItemList);

		return "admin/cart-item/cart-item-list";
	}

	// add
	@RequestMapping("/cart-item/add")
	public String add(Model model) {
		List<Cart> carts = cartService.search("");
		List<Product> products = productService.listproduct("");
		model.addAttribute("carts", carts);
		model.addAttribute("products", products);

		return "admin/cart-item/cart-item-add";
	}

	@PostMapping("/cart-item/add")
	public String add(Model model, @RequestParam("quantity") int quantity,
                      @RequestParam("idCart") int idCart, @RequestParam("idProduct") int idProduct) {
		CartItem ci = new CartItem();
		ci.setCart(cartService.get(idCart));
		ci.setProduct(productService.get(idProduct));
		ci.setPrice(productService.get(idProduct).getPrice());
		ci.setQuantity(quantity);

		cartItemService.add(ci);

		return "redirect:/admin/cart-item/list";
	}

	// del
	@GetMapping("/cart-item/delete/{id}")
	public String deleteCartItem(Model model, @PathVariable(value = "id") int id) {

		cartItemService.delete(id);
		return "redirect:/admin/cart-item/list";
	}

	// update
	@GetMapping("/cart-item/update/{id}")
	public String editCartItem(Model model, @PathVariable("id") int id) {
		List<Cart> carts = cartService.search("");
		List<Product> products = productService.listproduct("");
		model.addAttribute("carts", carts);
		model.addAttribute("products", products);

		CartItem cartItem = cartItemService.get(id);
		model.addAttribute("cartItem", cartItem);

		return "admin/cart-item/cart-item-update";
	}

	@PostMapping("/cart-item/update")
	public String editCartItem(Model model, @ModelAttribute CartItem cartItem) throws IOException {
		cartItemService.update(cartItem);

		return "redirect:/admin/cart-item/list";
	}

}
