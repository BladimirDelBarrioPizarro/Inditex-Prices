package com.inditex.prices.application.prices;
import com.inditex.prices.application.prices.dto.PricesResponse;
import com.inditex.prices.application.prices.exceptions.ErrorMessage;
import com.inditex.prices.application.prices.exceptions.NoPricesFoundException;
import com.inditex.prices.application.prices.map.PricesMapper;
import com.inditex.prices.domain.prices.Price;
import com.inditex.prices.infraestructure.prices.repository.PricesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PricesServiceImpl implements PricesService{


    private final PricesRepository pricesRepository;


    @Override
    public PricesResponse getPrices(LocalDateTime date, Long productId, Long brandId) {
        List<Price> prices = pricesRepository.findByStartDateLessThanEqualAndProductIdAndBrandIdOrderByPriorityDesc(date, productId,brandId);
        if (prices.isEmpty()) {
            throw new NoPricesFoundException(ErrorMessage.NO_PRICES_FOUND.getMessage());
        }
        Price selectedPrice = applicablePrice(prices);
        return PricesMapper.mapPricesToPricesResponse(selectedPrice);
    }



    private Price applicablePrice(List<Price> prices) {
        prices.sort(
                Comparator.comparing(Price::getStartDate).reversed()
                        .thenComparing(Price::getPriority).reversed()
        );
        return prices.get(0);
    }
}
