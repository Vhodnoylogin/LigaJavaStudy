package ru.liga.karmatskiyrg.model.currency;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
//@Value
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrencyRate {
    @CsvBindByName(column = "nominal")
    private Integer nominal;
    @CsvBindByName(column = "data")
    @CsvDate("M/d/yyyy")
    private LocalDate date;
    @CsvBindByName(column = "curs")
    private Double rate;
    @CsvBindByName(column = "cdx")
    private String name;
}
