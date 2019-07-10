package com.karma.controller;

import com.karma.model.Category;
import com.karma.model.Product;
import com.karma.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping("/category/add")
	public String addCategory(HttpServletRequest req) {

		return "admin/category/category-add";
	}

	@PostMapping("/category/add")
	public String addCategory(HttpServletRequest req, @RequestParam("id") String id,
                              @RequestParam("name") String name) {

		Category cate = new Category();
		cate.setName(name);

		categoryService.add(cate);

		return "redirect:/admin/category/list";
	}

	@GetMapping("/category/delete/{id}")
	public String deleteCategory(HttpServletRequest req, @PathVariable("id") int id) {

		Category cate = categoryService.get(id);
		req.setAttribute("categpry", cate);

		return "redirect:/admin/category/list";
	}

	@GetMapping("/category/update/{id}")
	public String updateCategory(HttpServletRequest req, @PathVariable("id") String id) {

		Category cate = categoryService.get(Integer.parseInt(id));
		req.setAttribute("category", cate);

		return "admin/category/category-update";
	}

	@PostMapping("/category/update")
	public String updateCategory(Model model, @ModelAttribute Category cate) {

		categoryService.update(cate);

		return "redirect:/admin/category/list";
	}

	@GetMapping("/category/list")
	public String categoryList(Model model) {
		List<Category> categories = categoryService.search("");
		for (Category c : categories) {
			System.out.println(c.getId() + c.getName());
		}
		model.addAttribute("categories", categories);
		return "admin/category/category-list";
	}

	@GetMapping("/category/list-product")
	public String productList(Model model) {
		List<Product> products = categoryService.searchByProduct("a");

		model.addAttribute("listProduct", products);
		return "admin/product/product-list";
	}
}
