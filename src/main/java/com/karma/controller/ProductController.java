package com.karma.controller;

import com.karma.model.Category;
import com.karma.model.Product;
import com.karma.service.CategoryService;
import com.karma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@GetMapping("/product/add")
	public String addProduct(HttpServletRequest req) {
		List<Category> categories = categoryService.search("");
		req.setAttribute("categories", categories);
		return "admin/product/product-add";
	}

	@PostMapping("/product/add")
	public String addProduct(HttpServletRequest req, @RequestParam("id") String id, @RequestParam("name") String name,
                             @RequestParam("description") String description, @RequestParam("price") String price,
                             @RequestParam("category") int cateId, @RequestParam("imageFile") MultipartFile imageFile)
			throws IOException {

		Product p = new Product();
//		p.setId(Integer.parseInt(id));
		p.setName(name);
		p.setPrice(Integer.parseInt(price));
		p.setDescription(description);
		p.setCategory(categoryService.get(cateId));

		// Save file to hard-disk
		// Get url , save to DB
		if (imageFile != null && !imageFile.isEmpty()) {
			String originalFileName = imageFile.getOriginalFilename();
			int lastIndex = originalFileName.lastIndexOf(".");
			String extFile = originalFileName.substring(lastIndex);

			String imageFileName = System.currentTimeMillis() + extFile;

			String folderLocation = "E:\\UPLOADFILE\\PRODUCT\\";

			try {
				FileOutputStream fileOutputStream = new FileOutputStream(folderLocation + imageFileName);

				fileOutputStream.write(imageFile.getBytes());
				fileOutputStream.flush();
				fileOutputStream.close();

				// save string file to db
				p.setImageFileName(imageFileName);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		}

		productService.add(p);
		return "redirect:/admin/product/list";
	}

	@GetMapping("/product/update/{id}")
	public String updateProduct(Model model, @PathVariable("id") int id) {

		List<Category> categories = categoryService.search("");
		model.addAttribute("categories", categories);
		Product p = productService.get(id);
		model.addAttribute("product", p);

		return "admin/product/product-update";
	}

	@PostMapping("/product/update")
	public String updateProduct(Model model, @ModelAttribute Product product) throws IOException {

		// Save file to hard-disk
		// Get url , save to DB
		if (product.getImageFile() != null && !product.getImageFile().isEmpty()) {
			String originalFileName = product.getImageFile().getOriginalFilename();
			int lastIndex = originalFileName.lastIndexOf(".");
			String extFile = originalFileName.substring(lastIndex);

			String imageFileName = System.currentTimeMillis() + extFile;

			String folderLocation = "E:\\UPLOADFILE\\PRODUCT\\";

			try {
				FileOutputStream fileOutputStream = new FileOutputStream(folderLocation + imageFileName);

				fileOutputStream.write(product.getImageFile().getBytes());
				fileOutputStream.flush();
				fileOutputStream.close();

				// save string file to db
				product.setImageFileName(imageFileName);
			} catch (FileNotFoundException e) {
				System.out.println(e);
			}
		}
		productService.update(product);
		return "redirect:/admin/product/list";
	}

	@GetMapping("/product/delete/{id}")
	public String deleteProduct(@PathVariable("id") int id) {

		productService.delete(id);
		return "redirect:/admin/product/list";
	}

	@GetMapping("/product/list")
	public String productList(Model model) {
		List<Product> products = productService.listproduct("");
		model.addAttribute("productList", products);
		return "admin/product/product-list";
	}

	@GetMapping("/product/list-category")
	public String productListCategory(Model model) {
		List<Product> products = productService.searchByCategory("Drink");
		model.addAttribute("productList", products);
		return "admin/product/product-list";
	}

	@PostMapping("/product/search")
	public String searchProduct(Model model, @RequestParam(value = "keyword") String name) {
		List<Product> productList = productService.search(name);
		model.addAttribute("productList", productList);

		return "admin/product/product-search";
	}

	// image
	@GetMapping("/product/image")
	public void download(HttpServletResponse resp, @RequestParam("filename") String fileName) {
		String folderLocation = "E:\\UPLOADFILE\\PRODUCT\\";
		try {
			File file = new File(folderLocation + fileName);

			// put image to Résp
			Files.copy(file.toPath(), resp.getOutputStream());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
