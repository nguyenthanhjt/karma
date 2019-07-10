package com.karma.dao;

import com.karma.model.Address;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class AddressDaoImpl implements AddressDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void add(Address address) {
		entityManager.persist(address);		
	}

	@Override
	public void update(Address address) {
		entityManager.merge(address);
	}

	@Override
	public void delete(Address address) {
		entityManager.remove(address);
	}

	@Override
	public Address get(int id) {
		return entityManager.find(Address.class, id);
	}

	@Override
	public List<Address> search(String name, int page, int max) {
//		String jpql = "SELECT add FROM Address add "
//				+ "WHERE add.name LIKE :param";
//		
//		TypedQuery<Address> query = 
//				entityManager.createQuery(jpql, Address.class);
//		
//		query.setParameter("param", "%" + name + "%");
		
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Address> query = builder.createQuery(Address.class);
		Root<Address> add = query.from(Address.class);
		
		if (name != null) {
			Predicate predicate = 
					builder.like(add.get("name"), "%" + name + "%");
			
			query.where(predicate);
		}
		query.select(add);
		
		TypedQuery<Address> typedQuery
			= entityManager.createQuery(query);
//			
		
		typedQuery.setFirstResult(page * max);//
		typedQuery.setMaxResults(max);///so ban ghi tren 1 trang
		return typedQuery.getResultList();
	}

	@Override
	public long count(String name) {
//		String jpql = "SELECT count(add) FROM Address add "
//				+ "WHERE add.name LIKE :param";
//		
//		TypedQuery<Long> query = 
//				entityManager.createQuery(jpql, Long.class);
//		query.setParameter("param", "%" + name + "%");
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Address> add = criteriaQuery.from(Address.class);
		
		if (name != null) {
			Predicate predicate = 
					builder.like(add.get("name"), "%" + name + "%");
			
			criteriaQuery.where(predicate);
		}
		criteriaQuery.select(builder.count(add));
		
		TypedQuery<Long> query = 
				entityManager.createQuery(criteriaQuery);
		return query.getSingleResult();
	}
}
