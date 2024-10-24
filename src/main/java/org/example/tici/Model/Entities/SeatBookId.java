package org.example.tici.Model.Entities;

import java.io.Serializable;
import java.util.Objects;

public class SeatBookId implements Serializable {
    private Integer seatNumber;
    private Integer booking;
    private Integer user;
    private Integer function;
    private String movie;
    private Integer projectionRoom;
    private Integer branch;


    public SeatBookId() {}


    public SeatBookId(Integer seatNumber, Integer booking, Integer user, Integer function, String movie, Integer projectionRoom, Integer branch) {
        this.seatNumber = seatNumber;
        this.booking = booking;
        this.user = user;
        this.function = function;
        this.movie = movie;
        this.projectionRoom = projectionRoom;
        this.branch = branch;
    }


    @Override
    public int hashCode() {
        return Objects.hash(seatNumber, booking, user, function, movie, projectionRoom, branch);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeatBookId that = (SeatBookId) o;
        return Objects.equals(seatNumber, that.seatNumber) &&
                Objects.equals(booking, that.booking) &&
                Objects.equals(user, that.user) &&
                Objects.equals(function, that.function) &&
                Objects.equals(movie, that.movie) &&
                Objects.equals(projectionRoom, that.projectionRoom) &&
                Objects.equals(branch, that.branch);
    }
}

