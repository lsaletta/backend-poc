package com.backend.poc.domain.repository;

import com.backend.poc.domain.entity.BrandEntity;
import org.springframework.data.repository.CrudRepository;

public interface BrandRepository extends CrudRepository<BrandEntity, Long> {
}
