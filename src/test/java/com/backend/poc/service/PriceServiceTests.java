package com.backend.poc.service;


import com.backend.poc.common.BaseTest;
import com.backend.poc.domain.entity.PriceEntity;
import com.backend.poc.domain.repository.PriceRepository;
import com.backend.poc.exception.BackendException;
import com.backend.poc.model.PriceDTO;
import com.backend.poc.service.impl.PriceServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTests extends BaseTest {

    private PriceService priceService;

    @Mock
    private PriceRepository priceRepository;

    private List<PriceEntity> pricesTest1;
    private List<PriceEntity> pricesTest2;
    private List<PriceEntity> pricesTest3;
    private List<PriceEntity> pricesTest4;


    @BeforeEach
    public void init() throws IOException {
        pricesTest1 =
                getFileFromListResources("jsons/Price_Test1.json", PriceEntity.class);
        pricesTest2 =
                getFileFromListResources("jsons/Price_Test2.json", PriceEntity.class);
        pricesTest3 =
                getFileFromListResources("jsons/Price_Test3.json", PriceEntity.class);
        pricesTest4 =
                getFileFromListResources("jsons/Price_Test4.json", PriceEntity.class);

        priceService = new PriceServiceImpl(priceRepository);
    }

    @Test
    void givenRepositoryOutputTest1_whenGetPrice_thenReturnPriceID1() throws IOException {
        mockedPriceRepository(pricesTest1);
        Optional<PriceDTO> prices = priceService.getPrices(LocalDateTime.now(), 1, 1);
        verify(priceRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
        asserts(prices, pricesTest1, 0);
    }

    @Test
    void givenRepositoryOutputTest2_whenGetPrice_thenReturnPriceID2() throws IOException {
        mockedPriceRepository(pricesTest2);
        Optional<PriceDTO> prices = priceService.getPrices(LocalDateTime.now(), 1, 1);
        verify(priceRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
        asserts(prices, pricesTest2, 1);
    }

    @Test
    void givenRepositoryOutputTest3_whenGetPrice_thenReturnPriceID3() throws IOException {
        mockedPriceRepository(pricesTest3);
        Optional<PriceDTO> prices = priceService.getPrices(LocalDateTime.now(), 1, 1);
        verify(priceRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
        asserts(prices, pricesTest3, 1);
    }

    @Test
    void givenRepositoryOutputTest4_whenGetPrice_thenReturnPriceID4() throws IOException {
        mockedPriceRepository(pricesTest4);
        Optional<PriceDTO> prices = priceService.getPrices(LocalDateTime.now(), 1, 1);
        verify(priceRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
        asserts(prices, pricesTest4, 1);
    }

    @Test
    void givenRepositoryOutputEmpty_whenGetPrice_thenThrowInditexException() throws IOException {
        mockedPriceRepository(Collections.emptyList());

        BackendException exception = Assertions.assertThrows(BackendException.class, () -> {
            priceService.getPrices(LocalDateTime.now(), 1, 1);
        });

        Assertions.assertEquals(exception.getErrorDescription(), "Price not found");
        verify(priceRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
    }

    @Test
    void givenRepositoryOutputNull_whenGetPrice_thenThrowBackendException() throws IOException {
        mockedPriceRepository(null);

        BackendException exception = Assertions.assertThrows(BackendException.class, () -> {
            priceService.getPrices(LocalDateTime.now(), 1, 1);
        });

        Assertions.assertEquals(exception.getErrorDescription(), "Error obtaining the price");
        verify(priceRepository, times(1))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
    }

    @Test
    void givenInputNull_whenGetPrice_thenThrowBackendException() {
        BackendException exception = Assertions.assertThrows(BackendException.class, () -> {
            priceService.getPrices(null, null, null);
        });

        Assertions.assertEquals(exception.getErrorDescription(), "Price not found");
        verify(priceRepository, times(0))
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt());
    }

    private void mockedPriceRepository(List<PriceEntity> mockedPrices) {
        Mockito.when(priceRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(any(
                        LocalDateTime.class), any(
                        LocalDateTime.class), anyInt(), anyInt()))
                .thenReturn(mockedPrices);
    }

    private void asserts(Optional<PriceDTO> prices, List<PriceEntity> mockedPrices, int index) {
        Assertions.assertTrue(prices.isPresent());
        Assertions.assertEquals(mockedPrices.get(index).getPrice(), prices.get().getPrice());
    }


}
