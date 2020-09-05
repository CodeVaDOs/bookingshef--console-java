package dao;

import entity.Booking;
import entity.Flight;
import enums.Destinations;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class AbstractDaoTest {
    BookingDao dao = new BookingDao();

    @Test
    void getAll() {
        dao.save(new Booking("Vadim", "Tartakovsky", new Flight(Instant.now(), Destinations.BERLIN, 24)));
        assertEquals(dao.getAll().size(), 1);
    }

    @Test
    void delete() {
        dao.save(new Booking("Vadim", "Tartakovsky", new Flight(Instant.now(), Destinations.BERLIN, 24)));
        assertEquals(dao.getAll().size(), 1);

        dao.delete(dao.getAll().get(0));
        assertEquals(dao.getAll().size(), 0);
    }

    @Test
    void save() {
        dao.save(new Booking("Vadim", "Tartakovsky", new Flight(Instant.now(), Destinations.BERLIN, 24)));
        assertEquals(dao.getAll().size(), 1);
    }
}