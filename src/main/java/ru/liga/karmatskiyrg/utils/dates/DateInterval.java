package ru.liga.karmatskiyrg.utils.dates;

import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Getter
public class DateInterval {
    private final @NonNull LocalDate startDate;
    private final @NonNull LocalDate endDate;

    public DateInterval(@NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        if (startDate.isBefore(endDate)) {
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            this.startDate = endDate;
            this.endDate = startDate;
        }
    }


    public static DateInterval of(LocalDate start, LocalDate end) {
        return new DateInterval(start, end);
    }

    public static boolean isIntersect(@NonNull DateInterval interval1, @NonNull DateInterval interval2) {
        return interval1.isIntersect(interval2);
    }

    public boolean dateIn(LocalDate date) {
        return
                this.startDate.isBefore(date)
                        && this.endDate.isAfter(date);
    }

    public long numberOfDays() {
        return ChronoUnit.DAYS.between(this.startDate, this.endDate);
    }

    public boolean isIntersect(DateInterval dateInterval) {
        if (dateInterval == this) return true;
        if (dateInterval.getEndDate().isBefore(this.startDate)) return false;
        return !dateInterval.getStartDate().isAfter(this.endDate);
    }
}
