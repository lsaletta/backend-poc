package com.backend.poc.service.impl;


import com.backend.poc.domain.repository.PriceRepository;
import com.backend.poc.model.PriceDTO;
import com.backend.poc.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;


    @Override
    public Optional<PriceDTO> getPrices(final LocalDateTime applicationDate, final Integer productId,
                                        final Integer brandId) {
        return Optional.empty();
    }
}
