package com.ixaris.interview.transfers;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transfer {
    @CsvBindByPosition(position = 0)
    private Long sourceAcct;

    @CsvBindByPosition(position = 1)
    private Long destinationAcct;

    @CsvBindByPosition(position = 2)
    private BigDecimal amount;

    @CsvBindByPosition(position = 3)
    @CsvDate("dd/MM/yyyy")
    private Date date;

    @CsvBindByPosition(position = 4)
    private int transferId;
}
