package dao;

import entity.Booking;

public class BookingDao extends AbstractDao<Booking> {
    public BookingDao() {
        super("booking.txt");
    }
}
