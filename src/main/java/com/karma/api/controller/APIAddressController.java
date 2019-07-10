package com.karma.api.controller;

import com.karma.dao.AddressDao;
import com.karma.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class APIAddressController {
    @Autowired
    private AddressDao addressDao;

    @GetMapping("/address") /// ?id=1
    public Address get(@RequestParam("id") int id) {
        return addressDao.get(id);
    }

    @PostMapping("/addresses") /// ?name=abc
    public List<Address> search(
            @RequestParam("name") String name,
            @RequestParam("page") int page,
            @RequestParam("max") int max) {
        return addressDao.search(name,page, max);
    }

    @PostMapping("/address")
    public Address add(@RequestBody Address address) {
        addressDao.add(address);
        return address;
    }

    @DeleteMapping("/address")///?id=1
    public void delete(@RequestParam("id") int id) {
        Address address = new Address();
        address.setId(id);

        addressDao.delete(address);
    }

    @PutMapping("/address")
    public void update(@RequestBody Address address) {
        addressDao.update(address);
    }



}
