package com.ixaris.interview.transfers;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Transfer {
    @CsvBindByPosition(position = 0)
    BigDecimal sourceAcct;

    @CsvBindByPosition(position = 1)
    BigDecimal destinationAcct;

    @CsvBindByPosition(position = 2)
    BigDecimal amount;

    @CsvBindByPosition(position = 3)
    @CsvDate("dd/MM/yyyy")
    Date date;

    @CsvBindByPosition(position = 4)
    int transferId;
}
