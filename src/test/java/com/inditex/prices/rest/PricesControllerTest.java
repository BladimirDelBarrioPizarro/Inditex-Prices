package com.inditex.prices.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.prices.application.prices.dto.PricesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class PricesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private void performPriceRequest(LocalDateTime date, Long productId, Long brandId) throws Exception {
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/prices")
                                .param("date", String.valueOf(date))
                                .param("productId", String.valueOf(productId))
                                .param("brandId", String.valueOf(brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        PricesResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PricesResponse.class);
        assertNotNull(response.getFinalPrice());
    }

    @Test
    @DisplayName("Test 1: Request at 10:00 on the 14th for product 35455, brand 1 (ZARA)")
    public void testRequestAt10AMOn14thForProduct35455Brand1() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 10, 0);
        performPriceRequest(localDateTime, 35455L, 1L);
    }

    @Test
    @DisplayName("Test 2: Request at 16:00 on the 14th for product 35455, brand 1 (ZARA)")
    public void testRequestAt4PMOn14thForProduct35455Brand1() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 16, 0);
        performPriceRequest(localDateTime, 35455L, 1L);
    }

    @Test
    @DisplayName("Test 3: Request at 21:00 on the 14th for product 35455, brand 1 (ZARA)")
    public void testRequestAt9PMOn14thForProduct35455Brand1() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 14, 21, 0);
        performPriceRequest(localDateTime, 35455L, 1L);
    }

    @Test
    @DisplayName("Test 4: Request at 10:00 on the 15th for product 35455, brand 1 (ZARA)")
    public void testRequestAt10AMOn15thForProduct35455Brand1() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 6, 15, 10, 0);
        performPriceRequest(localDateTime, 35455L, 1L);
    }

}
