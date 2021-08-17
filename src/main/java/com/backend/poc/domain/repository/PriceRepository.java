package com.backend.poc.domain.repository;

import com.backend.poc.domain.entity.PriceEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends CrudRepository<PriceEntity, Long> {

    List<PriceEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(
            LocalDateTime startDate,
            LocalDateTime endDate,
            Integer brandId,
            Integer productId);

}
