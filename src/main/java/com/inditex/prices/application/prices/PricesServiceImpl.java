package com.inditex.prices.application.prices;
import com.inditex.prices.application.prices.dto.PricesResponse;
import com.inditex.prices.application.prices.map.PricesMapper;
import com.inditex.prices.domain.prices.Price;
import com.inditex.prices.infraestructure.prices.repository.PricesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class PricesServiceImpl implements PricesService{

    @Autowired
    private PricesRepository pricesRepository;


    @Override
    public PricesResponse getPrices(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = pricesRepository.findByStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, productId,brandId);

        Price selectedPrice = applicablePrice(prices);
        if (prices.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron precios para la fecha, el producto y la marca proporcionados.");
        }
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
