package com.inditex.prices.infraestructure.prices.repository;
import com.inditex.prices.domain.prices.Prices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface PricesRepository extends JpaRepository<Prices, Long> {
    List<Prices> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(
            LocalDateTime dateApp,
            Long productId,
            Long brandId
    );
}
