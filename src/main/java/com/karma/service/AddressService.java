package com.karma.service;

import com.karma.model.Address;

import java.util.List;

public interface AddressService {
	void add(Address address);

	void update(Address address);

	void delete(int id);

	Address get(int id);

	List<Address> search(String name, int page, int max);
}
