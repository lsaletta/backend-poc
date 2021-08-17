package com.backend.poc.service.impl;


import com.backend.poc.domain.entity.PriceEntity;
import com.backend.poc.domain.repository.PriceRepository;
import com.backend.poc.exception.BackendException;
import com.backend.poc.mapper.PriceMapper;
import com.backend.poc.model.PriceDTO;
import com.backend.poc.service.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;


    @Override
    public Optional<PriceDTO> getPrices(final LocalDateTime applicationDate, final Integer productId,
                                        final Integer brandId) {
        try {
            List<PriceEntity> candidatePrices = priceRepository
                    .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(
                            applicationDate,
                            applicationDate,
                            brandId, productId);
            return Optional
                    .ofNullable(PriceMapper.INSTANCE.toDto(filterPricesByPriority(candidatePrices)));
        } catch (NoSuchElementException e) {
            log.error("NoSuchElementException: ", e);
            throw new BackendException("Price not found", e);
        } catch (Exception e) {
            log.error("Exception: ", e);
            throw new BackendException("Error obtaining the price", e);
        }
    }

    private PriceEntity filterPricesByPriority(final List<PriceEntity> candidatePrices) {
        return candidatePrices.stream().max(Comparator.comparing(PriceEntity::getPriority))
                .orElseThrow(NoSuchElementException::new);
    }
}
