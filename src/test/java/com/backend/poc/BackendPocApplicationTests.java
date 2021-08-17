package com.backend.poc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BackendPocApplicationTests {

    public static final String PRICE_ENDPOINT = "/backend/price";
    @Autowired
    private MockMvc mvc;

    @Test
    public void givenInputTest1_whenGetPrice_thenReturnPriceID1()
            throws Exception {
        mvc.perform(get(PRICE_ENDPOINT)
                .param("application_date", "2020-06-14 10:00:00")
                .param("product_id", "35455")
                .param("brand_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5"));
    }

    @Test
    public void givenInputTest2_whenGetPrice_thenReturnPriceID2()
            throws Exception {
        mvc.perform(get(PRICE_ENDPOINT)
                .param("application_date", "2020-06-14 16:00:00")
                .param("product_id", "35455")
                .param("brand_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("25.45"));
    }

    @Test
    public void givenInputTest3_whenGetPrice_thenReturnPriceID1()
            throws Exception {
        mvc.perform(get(PRICE_ENDPOINT)
                .param("application_date", "2020-06-14 21:00:00")
                .param("product_id", "35455")
                .param("brand_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5"));
    }

    @Test
    public void givenInputTest4_whenGetPrice_thenReturnPriceID3()
            throws Exception {
        mvc.perform(get(PRICE_ENDPOINT)
                .param("application_date", "2020-06-15 10:00:00")
                .param("product_id", "35455")
                .param("brand_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("30.5"));
    }

    @Test
    public void givenInputTest5_whenGetPrice_thenReturnPriceID4()
            throws Exception {
        mvc.perform(get(PRICE_ENDPOINT)
                .param("application_date", "2020-06-16 21:00:00")
                .param("product_id", "35455")
                .param("brand_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("38.95"));
    }

    @Test
    public void givenInputTest5_whenGetPrice_thenReturnNotContentPrice()
            throws Exception {
        mvc.perform(get(PRICE_ENDPOINT)
                .param("application_date", "2020-06-16 21:00:00")
                .param("product_id", "99999")
                .param("brand_id", "1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.message").value("Price not found"));
    }
}
