
package ch.zli.m223.service;

import ch.zli.m223.dto.TimeSummaryDto;
import ch.zli.m223.model.Entry;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeSummaryServiceTest {

    @Test
    void singleEntry() {

        // Arrange
        TimeSummaryService service = new TimeSummaryService();

        Entry entry = new Entry();

        entry.setCheckIn(
            LocalDateTime.of(2026, 11, 3, 8, 0)
        );

        entry.setCheckOut(
            LocalDateTime.of(2026, 11, 3, 12, 0)
        );

        // Act
        List<TimeSummaryDto> result =
            service.CalculateSummaryPerDay(List.of(entry));

        // Assert
        assertEquals(1, result.size());

        assertEquals(
            LocalDate.of(2026, 11, 3),
            result.get(0).date()
        );

        assertEquals(
            Duration.ofHours(4),
            result.get(0).duration()
        );
    }

    
@Test
void twoEntries() {

    // Arrange
    TimeSummaryService service = new TimeSummaryService();

    Entry entry1 = new Entry();
    entry1.setCheckIn(
        LocalDateTime.of(2026, 11, 3, 8, 0)
    );
    entry1.setCheckOut(
        LocalDateTime.of(2026, 11, 3, 12, 0)
    );

    Entry entry2 = new Entry();
    entry2.setCheckIn(
        LocalDateTime.of(2026, 11, 3, 13, 0)
    );
    entry2.setCheckOut(
        LocalDateTime.of(2026, 11, 3, 17, 0)
    );

    // Act
    List<TimeSummaryDto> result =
        service.CalculateSummaryPerDay(
            List.of(entry1, entry2)
        );

    // Assert
    assertEquals(1, result.size());

    assertEquals(
        LocalDate.of(2026, 11, 3),
        result.get(0).date()
    );

    assertEquals(
        Duration.ofHours(8),
        result.get(0).duration()
    );
}



@Test
void overnightEntry() {

    // Arrange
    TimeSummaryService service = new TimeSummaryService();

    Entry entry = new Entry();

    entry.setCheckIn(
        LocalDateTime.of(2026, 11, 3, 22, 0)
    );

    entry.setCheckOut(
        LocalDateTime.of(2026, 11, 4, 2, 0)
    );

    // Act
    List<TimeSummaryDto> result =
        service.CalculateSummaryPerDay(List.of(entry));

    // Assert
    assertEquals(2, result.size());

    assertEquals(
        LocalDate.of(2026, 11, 3),
        result.get(0).date()
    );

    assertEquals(
        Duration.ofHours(2),
        result.get(0).duration()
    );

    assertEquals(
        LocalDate.of(2026, 11, 4),
        result.get(1).date()
    );

    assertEquals(
        Duration.ofHours(2),
        result.get(1).duration()
    );
}

}
