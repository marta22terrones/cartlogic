package com.API.testAPI.repository;


import com.API.testAPI.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.lastActivity < :threshold")
    List<Cart> findByLastActivityBefore(@Param("threshold") LocalDateTime threshold);

}
