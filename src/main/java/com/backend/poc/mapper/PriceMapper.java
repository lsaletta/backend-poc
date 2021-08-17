package com.backend.poc.mapper;

import com.backend.poc.domain.entity.PriceEntity;
import com.backend.poc.model.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {
    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(target = "currency", source = "curr")
    PriceDTO toDto(PriceEntity priceEntity);
}
