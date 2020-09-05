package dao;

import entity.Flight;

public class FlightDao extends AbstractDao<Flight> {
    public FlightDao() {
        super("flight.txt");
    }
}
