package org.example.tici.Model.Entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
@Table(name = "function")
public class Function {

    @Id
    @Column(name = "id_function")
    private int idFun;

    @Column(name = "day_month", nullable = false)
    private int dayMonth;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "projection_room_number", nullable = false)
    private int projectionRoomNumber;

    @Column(name = "branch_id", nullable = false)
    private int branchId;

    @ManyToOne
    @JoinColumn (name = "movie_title", nullable = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projection_room_number", insertable = false, updatable = false)
    private ProjectionRoom projectionRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id",insertable = false, updatable = false)
    private Branches branches;

    public Function(){

    }
    public Function(int idFun, int date,LocalTime startTime,
                    LocalTime endTime,ProjectionRoom projectionRoom, Movie movieTitle ){
        this.idFun=idFun;
        this.dayMonth=date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectionRoom = projectionRoom;
        this.movie = movieTitle;
    }

    public int getIdFun() {
        return idFun;
    }

    public void setIdFun(int idFun) {
        this.idFun = idFun;
    }

    public int getDate() {
        return dayMonth;
    }

    public void setDate(int date) {
        this.dayMonth = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime start_time) {
        this.startTime = start_time;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime end_time) {
        this.endTime = end_time;
    }

    public ProjectionRoom getProjectionRoom() {
        return projectionRoom;
    }

    public void setProjectionRoom(ProjectionRoom projectionRoom) {
        this.projectionRoom = projectionRoom;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie_id(Movie movie_title) {
        this.movie = movie_title;
    }
}
