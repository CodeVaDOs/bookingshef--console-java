package service;

import dao.AbstractDao;
import dao.BookingDao;
import entity.Booking;
import entity.Flight;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookingService {
    private AbstractDao<Booking> dao = new BookingDao();

    public Optional<Booking> reserve(String name, String surname, Flight flight) {
        if (flight.getCountPlaces() > 0) {
            flight.setCountPlaces(flight.getCountPlaces() - 1);
            return Optional.ofNullable(dao.save(new Booking(name, surname, flight)));
        } else {
            return Optional.empty();
        }
    }

    ;

    public boolean cancelReserve(long id) {
        return dao.getAll().stream().filter(i -> i.getId() == id).findFirst()
                .map(i -> dao.delete(i)).orElse(false);
    }

    public Optional<Booking> getById(long id) {
        return dao.getAll()
                .stream()
                .filter(b -> b.getId() == id)
                .findFirst();
    }

    public boolean saveDataToFile() {
        return dao.saveDataToFile();
    }

    public List<Booking> findFlightByCredit(String name, String surname) {
        return dao.getAll()
                .stream()
                .filter(f ->
                        f.getClientName().equalsIgnoreCase(name) &&
                                f.getClientSurname().equalsIgnoreCase(surname)
                )
                .collect(Collectors.toList());

    }

    public List<Booking> getAll() {
        return this.dao.getAll();
    }
}
