package com.karma.dao;

import com.karma.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.name LIKE :param")
	List<User> search(@Param("param") String name);
	@Query("SELECT u FROM User u WHERE u.username LIKE :username")
	User getByUsername(@Param("username") String username);

	@Query("SELECT u FROM User u ")
	List<User> getAll(String name);

	@Query("SELECT u FROM User u WHERE u.id = :param")
	User get(@Param("param") int id);

	@Modifying
	@Query(value = "INSERT INTO User (age,name,password,role,username) values (:age, :name, :password, :role, :username)", nativeQuery = true)
	void addUser(@Param("name") String name, @Param("username") String username, @Param("password") String password,
                 @Param("role") String role, @Param("age") int age);
	@Query("SELECT u FROM User u  JOIN u.address ad WHERE ad.name = :param")
	User getByAddress(@Param("param") int id);
	
//	void add(User user);
//
//	void update(User user);
//
//	void delete(User user);
//
//	User get(int id);
//
//	List<User> search(String name);
}
