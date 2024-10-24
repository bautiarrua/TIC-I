package org.example.tici.Model.Entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import java.util.Set;

@Entity
@Table (name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users userId;

    @ManyToOne
    @JoinColumn(name = "id_function", nullable = false)
    private Function function;

    @ManyToOne
    @JoinColumn(name = "movie_title", referencedColumnName = "title", nullable = false)
    private Movie movieTitle;

    @ManyToOne
    @JoinColumn(name = "projection_room", nullable = false)
    private ProjectionRoom projectionRoom;

    @ManyToOne
    @JoinColumn(name = "id_branch", nullable = false)
    private Branches branchId;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private Set<SeatBook> seats;

    public Booking() {
    }

    public Booking(int bookingId, Users userId, Function function, Movie movieTitle, ProjectionRoom projectionRoom, Branches branchId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.function = function;
        this.movieTitle = movieTitle;
        this.projectionRoom = projectionRoom;
        this.branchId = branchId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Function getFunction() {
        return function;
    }

    public void setFunction(Function function) {
        this.function = function;
    }

    public Movie getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(Movie movieTitle) {
        this.movieTitle = movieTitle;
    }

    public ProjectionRoom getProjectionRoom() {
        return projectionRoom;
    }

    public void setProjectionRoom(ProjectionRoom projectionRoom) {
        this.projectionRoom = projectionRoom;
    }

    public Branches getBranchId() {
        return branchId;
    }

    public void setBranchId(Branches branchId) {
        this.branchId = branchId;
    }

    public Set<SeatBook> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatBook> seats) {
        this.seats = seats;
    }

}
