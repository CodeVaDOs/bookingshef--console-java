package entity;

import java.util.Objects;
import java.util.UUID;

public class Booking extends AbstractEntity {
    private String clientName;
    private String clientSurname;
    private Flight flight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(clientName, booking.clientName) &&
                Objects.equals(clientSurname, booking.clientSurname) &&
                Objects.equals(flight, booking.flight) &&
                getId() == booking.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), clientName, clientSurname, flight, getId());
    }

    public Booking(String clientName, String clientSurname, Flight flight) {
        super(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.flight = flight;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id='" + getId() + '\'' +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", flight=" + flight +
                '}';
    }
}
