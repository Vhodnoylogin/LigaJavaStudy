package ru.liga.karmatskiyrg.distributed.app.client.utils.dates.interfaces;

import lombok.NonNull;
import ru.liga.karmatskiyrg.distributed.app.client.utils.dates.DatePeriod;

import java.time.LocalDate;

public interface DateInterval {
    static boolean isIntersect(@NonNull DateInterval interval1, @NonNull DateInterval interval2) {
        return interval1.isIntersect(interval2);
    }

    static DateInterval of(@NonNull LocalDate start, @NonNull LocalDate end) {
        return DatePeriod.of(start, end);
    }

    boolean dateIn(@NonNull LocalDate date);

    long numberOfDays();

    boolean isIntersect(@NonNull DateInterval dateInterval);

    boolean isIntersect(@NonNull LocalDate date);

    LocalDate startDate();

    LocalDate endDate();
}
