package entity;

import enums.Destinations;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Flight extends AbstractEntity {
    private Instant dateTime;
    private Destinations destination;
    private int countPlaces;

    public Flight(Instant dateTime, Destinations destination, int countPlaces) {
        super(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        this.dateTime = dateTime;
        this.destination = destination;
        this.countPlaces = countPlaces;
    }

    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public Destinations getDestination() {
        return destination;
    }

    public void setDestination(Destinations destination) {
        this.destination = destination;
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return countPlaces == flight.countPlaces &&
                Objects.equals(dateTime, flight.dateTime) &&
                destination == flight.destination &&
                getId() == flight.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, destination, countPlaces, getId());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + getId() +
                ", dateTime=" + dateTime +
                ", destination=" + destination +
                ", countPlaces=" + countPlaces +
                '}';
    }
}
