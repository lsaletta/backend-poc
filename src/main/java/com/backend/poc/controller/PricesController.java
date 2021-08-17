package com.backend.poc.controller;

import com.backend.poc.model.PriceDTO;
import com.backend.poc.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/backend")
@RequiredArgsConstructor
public class PricesController {

    private final PriceService priceService;

    @RequestMapping(path = "/price", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get price that match with the inputs fields")
    public ResponseEntity<PriceDTO> getPrice(
            @RequestParam(name = "application_date", required = true)
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime applicationDate,
            @RequestParam(name = "product_id", required = true) Integer productId,
            @RequestParam(name = "brand_id", required = true) Integer brandId) {
        log.info(">>> Launch getPrice Operation");

        return priceService.getPrices(applicationDate, productId, brandId)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
