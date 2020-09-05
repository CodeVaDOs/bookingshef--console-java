package controller;

import entity.Booking;
import entity.Flight;
import service.BookingService;

import java.util.List;
import java.util.Optional;

public class BookingController {
    private BookingService service = new BookingService();

    public Optional<Booking> reserve(String name, String surname, Flight flight) {
        return service.reserve(name, surname, flight);
    }

    public boolean cancelReserve(long id) {
        return service.cancelReserve(id);
    }

    public Optional<Booking> getById(long id) {
        return service.getById(id);
    }

    public boolean saveDataToFile() {
        return service.saveDataToFile();
    }

    public List<Booking> findFlightByCredit(String name, String surname) {
        return service.findFlightByCredit(name, surname);
    }

}
