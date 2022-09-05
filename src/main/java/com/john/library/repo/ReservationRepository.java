package com.john.library.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.john.library.entity.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
	Reservation findByBookingNumber(String bnumber);
	List<Reservation> findByUserId(long userId);
	
	@Transactional
	@Modifying
	@Query(value = "update Reservation r set r.status = :status where r.id = :rId", nativeQuery = true)
	void updateStatus(@Param("rId") long rId, @Param("status") String status);
}
