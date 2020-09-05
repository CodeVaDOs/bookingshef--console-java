package service;

import dao.AbstractDao;
import dao.FlightDao;
import entity.Flight;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightService {
    private AbstractDao<Flight> dao = new FlightDao();

    public Optional<Flight> save(Flight flight) {
        return Optional.ofNullable(dao.save(flight));
    }

    public void saveDataToFile() {
        dao.saveDataToFile();
    }

    public List<Flight> getOnlineFlights() {
        return dao.getAll().stream()
                .filter(i ->
                        Duration.between(Instant.now(), i.getDateTime())
                                .toMillis() <= 86400000 && Duration.between(Instant.now(),
                                i.getDateTime()).toMillis() >= 0
                )
                .collect(Collectors.toList());
    }

    public Optional<Flight> getFlightInfo(long id) {
        return dao.getAll().stream()
                .filter(i -> i.getId() == id)
                .findFirst();
    }
}
