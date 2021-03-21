package com.ixaris.interview.transfers.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author johard
 */
@Data
public class Transfer extends com.ixaris.interview.transfers.domain.Transfer {
    @JsonFormat(pattern = "dd/MM/yyyy") @NonNull final LocalDate date;
    @NonNull final Long sourceAcct;
    @NonNull final Long destinationAcct;
    @NonNull final BigDecimal amount;
}
