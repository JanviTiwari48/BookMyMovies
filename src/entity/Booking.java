package entity;

import java.util.List;

public class Booking {
    private int bookingid;
    private int userId;
    private int showId;

    private List<String> seatBooked;
    private double totalPrice;

    public Booking(int bookingid, int userId, int showId, List<String> seatBooked, double totalPrice) {
        this.bookingid = bookingid;
        this.userId = userId;
        this.showId = showId;
        this.seatBooked = seatBooked;
        this.totalPrice = totalPrice;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShowId() {
        return showId;
    }

    public void setShowId(int showId) {
        this.showId = showId;
    }

    public List<String> getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(List<String> seatBooked) {
        this.seatBooked = seatBooked;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
