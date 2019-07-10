package com.karma.dao;

import com.karma.model.Address;

import java.util.List;

public interface AddressDao {
	void add(Address address);

	void update(Address address);

	void delete(Address address);

	Address get(int id);

	List<Address> search(String name, int page, int max);
	
	long count(String name);
}
