package com.karma.service;

import com.karma.model.User;

import java.util.List;

public interface UserService {
	void add(User user);

	void update(User user);

	void delete(int id);

	User get(int id);

	List<User> search(String name);

}
