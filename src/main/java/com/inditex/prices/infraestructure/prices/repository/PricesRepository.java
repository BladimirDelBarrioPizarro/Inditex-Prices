package com.inditex.prices.infraestructure.prices.repository;
import com.inditex.prices.domain.prices.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PricesRepository extends JpaRepository<Price, Long> {
    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualOrderByPriorityDesc(
            Long brandId,
            LocalDateTime dateApp,
            Long productId
    );

}
