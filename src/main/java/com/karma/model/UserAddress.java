package com.karma.model;//package com.trungtamjava.hellospringboot.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
//@Entity
//@Table(name = "user_addres")
//public class UserAddress {
//
//	@Id
//	@Column(name = "id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
//
//	@ManyToOne
//	@JoinColumn(name = "user_id")
//	private User user;
//
//	@ManyToOne
//	@JoinColumn(name = "address_id")
//	private Address add;
//
//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public Address getAdd() {
//		return add;
//	}
//
//	public void setAdd(Address add) {
//		this.add = add;
//	}
//
//}
