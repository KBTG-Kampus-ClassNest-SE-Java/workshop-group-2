package com.kampus.kbazaar.cart;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {

    List<CartDetail> findByCartId(Integer cartId);
}
