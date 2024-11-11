package org.example.tici.DTO;

import java.util.List;

public class CancelRequest {
    private int bookingId;
    private List<Integer> seatsToCancel;

    public CancelRequest() {
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public List<Integer> getSeatsToCancel() {
        return seatsToCancel;
    }

    public void setSeatsToCancel(List<Integer> seatsToCancel) {
        this.seatsToCancel = seatsToCancel;
    }
}
