package com.API.testAPI.repository;

import com.API.testAPI.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Long> {
}
