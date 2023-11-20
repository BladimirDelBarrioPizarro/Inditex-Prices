package com.inditex.prices.application.prices;
import com.inditex.prices.application.prices.dto.PricesResponse;
import com.inditex.prices.application.prices.map.PricesMapper;
import com.inditex.prices.domain.prices.Price;
import com.inditex.prices.infraestructure.prices.repository.PricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
public class PricesServiceImpl implements PricesService{

    @Autowired
    private PricesRepository pricesRepository;


    @Override
    public PricesResponse getPrices(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = pricesRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualOrderByPriorityDesc(brandId, date, productId);
        Price selectedPrice = applicablePrice(prices);
        assert selectedPrice != null;
        return PricesMapper.mapPricesToPricesResponse(selectedPrice);

    }

    private Price applicablePrice(List<Price> prices) {
        // Ordena la lista de tarifas por fecha de inicio de forma descendente
        // y luego por prioridad de forma descendente
        prices.sort(
                Comparator.comparing(Price::getStartDate).reversed()
                        .thenComparing(Price::getPriority).reversed()
        );

        // La primera tarifa en la lista ordenada tiene la mayor prioridad
        return prices.get(0);
    }
}
