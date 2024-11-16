package org.example.tici.DTO;

import java.time.LocalTime;
import java.util.List;

public class BookingResponseDTO {
    private String branchNeighborhood; // Barrio del cine
    private LocalTime startTime; // Hora de inicio
    private LocalTime endTime; // Hora de fin
    private String movieTitle; // Título de la película
    private List<Integer> seats; // Lista de asientos reservados

    public BookingResponseDTO(String branchNeighborhood, LocalTime startTime, LocalTime endTime, String movieTitle, List<Integer> seats) {
        this.branchNeighborhood = branchNeighborhood;
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieTitle = movieTitle;
        this.seats = seats;
    }

    public String getBranchNeighborhood() {
        return branchNeighborhood;
    }

    public void setBranchNeighborhood(String branchNeighborhood) {
        this.branchNeighborhood = branchNeighborhood;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public List<Integer> getSeats() {
        return seats;
    }

    public void setSeats(List<Integer> seats) {
        this.seats = seats;
    }
}
