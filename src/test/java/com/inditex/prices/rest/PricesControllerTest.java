package com.inditex.prices.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.prices.application.prices.dto.PricesResponse;
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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


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






    @Test
    public void testGetPrices() throws Exception {

       // Timestamp date = Timestamp.valueOf(LocalDateTime.of(2020, 6, 14, 0, 0, 0));
        String date = "2020-06-14 00:00:00";
        Long productId = 35455L;
        Long brandId = 1L;


        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/prices")
                                .param("date", date)
                                .param("productId", String.valueOf(productId))
                                .param("brandId", String.valueOf(brandId))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        PricesResponse response = objectMapper.readValue(result.getResponse().getContentAsString(), PricesResponse.class);

    }
}
