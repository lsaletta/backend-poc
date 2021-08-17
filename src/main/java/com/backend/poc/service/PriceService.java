package com.backend.poc.service;

import com.backend.poc.model.PriceDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public interface PriceService {
    Optional<PriceDTO> getPrices(LocalDateTime applicationDate, Integer productId, Integer brandId);
}
