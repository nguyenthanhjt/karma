package com.karma.service.impl;

import com.karma.dao.CategoryDao;
import com.karma.model.Category;
import com.karma.model.Product;
import com.karma.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public void add(Category cate) {

		categoryDao.save(cate);
	}

	@Override
	public void delete(int id) {
		categoryDao.deleteById(id);

	}

	@Override
	public void update(Category cate) {
		Category currentCategory = get(cate.getId());
		if (currentCategory != null) {

			currentCategory.setId(cate.getId());
			currentCategory.setName(cate.getName());

			categoryDao.save(currentCategory);
		}

	}

	@Override
	public Category get(int id) {

		return categoryDao.findById(id).orElse(null);
	}

	@Override
	public List<Category> search(String name) {

		return categoryDao.list();
	}

	@Override
	public List<Product> searchByProduct(String name) {
		List<Product> listProduct = categoryDao.searchByProduct(name);
		return listProduct;
	}

}
