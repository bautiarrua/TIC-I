package org.example.tici.Model.Entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "seats_book")
@IdClass(SeatBookId.class)
public class SeatBook implements Serializable {

    @Id
    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Booking booking;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_function", nullable = false)
    private Function function;

    @Id
    @ManyToOne
    @JoinColumn(name = "movie_title", referencedColumnName = "title", nullable = false)
    private Movie movie;

    @Id
    @ManyToOne
    @JoinColumn(name = "projection_room_number", nullable = false)
    private ProjectionRoom projectionRoom;

    @Id
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branches branch;

    // Getters y Setters
    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public ProjectionRoom getProjectionRoom() {
        return projectionRoom;
    }

    public void setProjectionRoom(ProjectionRoom projectionRoom) {
        this.projectionRoom = projectionRoom;
    }

    public Branches getBranch() {
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }

}
