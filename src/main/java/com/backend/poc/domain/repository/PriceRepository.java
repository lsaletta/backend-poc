package com.backend.poc.domain.repository;

import com.backend.poc.domain.entity.PriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface PriceRepository extends CrudRepository<PriceEntity, Long> {

}
