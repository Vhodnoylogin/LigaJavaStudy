package ru.liga.karmatskiyrg.distributed.app.client.utils.dates;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.interfaces.DateInterval;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@ToString
@EqualsAndHashCode
public class DatePeriod implements DateInterval {
    private final @NonNull LocalDate startDate;
    private final @NonNull LocalDate endDate;

    public DatePeriod(@NonNull LocalDate startDate, @NonNull LocalDate endDate) {
        if (startDate.isBefore(endDate)) {
            this.startDate = startDate;
            this.endDate = endDate;
        } else {
            this.startDate = endDate;
            this.endDate = startDate;
        }
    }


    public static DateInterval of(@NonNull LocalDate start, @NonNull LocalDate end) {
        return new DatePeriod(start, end);
    }


    @Override
    public boolean dateIn(@NonNull LocalDate date) {
        return
                !(date.isBefore(this.startDate) ||
                        date.isAfter(this.endDate));
    }

    @Override
    public long numberOfDays() {
        return ChronoUnit.DAYS.between(this.startDate, this.endDate) + 1;
    }

    @Override
    public boolean isIntersect(@NonNull DateInterval dateInterval) {
        if (dateInterval == this) return true;
        if (dateInterval.endDate().isBefore(this.startDate)) return false;
        return !dateInterval.startDate().isAfter(this.endDate);
    }

    public boolean isIntersect(@NonNull LocalDate date) {
        return this.dateIn(date);
    }

    @Override
    public LocalDate startDate() {
        return this.startDate;
    }

    @Override
    public LocalDate endDate() {
        return this.endDate;
    }
}
