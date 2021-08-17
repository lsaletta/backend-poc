package com.backend.poc.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class BackendException extends RuntimeException {

    private static final long serialVersionUID = -4105726199013772984L;
    private String errorDescription;

    public BackendException(final String errorDescription, final Throwable throwable) {
        super(throwable);
        this.errorDescription = errorDescription;
    }
}
