package ru.liga.karmatskiyrg.model.currency;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
@Value
@Builder
@AllArgsConstructor
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

//    public CurrencyRate(Integer nominal, LocalDate date, Double rate, String name) {
//        this.nominal = nominal;
//        this.date = date;
//        this.rate = rate;
//        this.name = name;
//    }
//
//    public CurrencyRate() {
//    }

//    @Override
//    public String toString() {
//        return "CurrencyRate{" +
//                "nominal=" + nominal +
//                ", date=" + date +
//                ", rate=" + rate +
//                ", name='" + name + '\'' +
//                '}';
//    }
//
//    public Integer getNominal() {
//        return nominal;
//    }
//
//    public LocalDate getDate() {
//        return date;
//    }
//
//    public Double getRate() {
//        return rate;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CurrencyRate that)) return false;
//
//        if (getNominal() != null ? !getNominal().equals(that.getNominal()) : that.getNominal() != null) return false;
//        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null) return false;
//        if (getRate() != null ? !getRate().equals(that.getRate()) : that.getRate() != null) return false;
//        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = getNominal() != null ? getNominal().hashCode() : 0;
//        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
//        result = 31 * result + (getRate() != null ? getRate().hashCode() : 0);
//        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
//        return result;
//    }
}
