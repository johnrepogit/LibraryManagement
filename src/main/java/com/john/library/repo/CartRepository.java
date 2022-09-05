package com.john.library.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.john.library.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	Cart findByUserIdAndStatus(long userId, String status);
	
	@Transactional
	@Modifying
	@Query(value ="update cart c set c.status = :status where c.id = :cId", nativeQuery = true)
	void updateCartStatus(@Param("cId") long cId, @Param("status") String status);
}
