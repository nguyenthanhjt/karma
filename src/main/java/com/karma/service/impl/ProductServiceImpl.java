package com.karma.service.impl;

import com.karma.dao.ProductDao;
import com.karma.model.Product;
import com.karma.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public void add(Product p) {

		productDao.save(p);
	}

	@Override
	public void update(Product p) {
		Product currentProduct = get(p.getId());
		if (currentProduct != null) {

			currentProduct.setId(p.getId());
			currentProduct.setName(p.getName());
			currentProduct.setPrice(p.getPrice());
			currentProduct.setDescription(p.getDescription());
			if (p.getImageFileName() != null) {
				String filename = p.getImageFileName();
				String folderLocation = "E:\\UPLOADFILE\\PRODUCT\\";
				File file = new File(folderLocation + filename);
				if (file.exists()) {
//					file.delete();
				}
				currentProduct.setImageFileName(p.getImageFileName());
			}

			productDao.save(currentProduct);
		}
	}

	@Override
	public void delete(int id) {

		productDao.deleteById(id);
	}

	@Override
	public Product get(int id) {

		return productDao.findById(id).orElse(null);
	}

	@Override
	public List<Product> listproduct(String name) {
		List<Product> listProduct = productDao.findAll();
		return listProduct;
	}

	@Override
	public List<Product> search(String name) {
		List<Product> listProduct = productDao.search("%" + name + "%");
		return listProduct;
	}

	@Override
	public List<Product> searchByCategory(String name) {
		List<Product> listProduct = productDao.searchByCategory(name);
		return null;
	}

}
