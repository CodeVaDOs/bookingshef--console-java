package service;

import dao.BookingDao;
import dao.FlightDao;
import entity.Flight;
import enums.Destinations;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    BookingService bService = new BookingService();
    FlightService fService = new FlightService();

    @Test
    void reserve() {
        Optional<Flight> flight = fService.save(new Flight(Instant.now(), Destinations.BERLIN, 10));
        flight.flatMap(f -> bService.reserve("Vadim", "Tartakovsky", f));

        assertEquals(1, bService.getAll().size());
    }

    @Test
    void cancelReserve() {
        Optional<Flight> flight = fService.save(new Flight(Instant.now(), Destinations.BERLIN, 10));
        flight.flatMap(f -> bService.reserve("Vadim", "Tartakovsky", f));

        assertEquals(1, bService.getAll().size());

        bService.cancelReserve(bService.getAll().get(0).getId());

        assertEquals(0, bService.getAll().size());
    }
}