package controller;

import entity.Flight;
import service.FlightService;

import java.util.List;
import java.util.Optional;

public class FlightController {
    private FlightService service = new FlightService();

    public Optional<Flight> save(Flight flight) {
        return service.save(flight);
    }

    public void saveDataToFile() {
        service.saveDataToFile();
    }

    public Optional<Flight> getFlightInfo(long id) {
        return service.getFlightInfo(id);
    }


    public List<Flight> getOnlineFlights() {
        return service.getOnlineFlights();
    }
}
