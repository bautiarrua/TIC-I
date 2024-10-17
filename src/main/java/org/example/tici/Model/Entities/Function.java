package org.example.tici.Model.Entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
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

    @Column(name = "movie_title", nullable = false, length = 200)
    private String movieTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projection_room_number", insertable = false, updatable = false)
    private ProjectionRoom projectionRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_title", insertable = false, updatable = false)
    private Movie movie;

    public Function(){

    }
    public Function(int idFun, int date,LocalTime startTime,
                    LocalTime endTime,ProjectionRoom projectionRoom, String movieTitle ){
        this.idFun=idFun;
        this.dayMonth=date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.projectionRoom = projectionRoom;
        this.movieTitle = movieTitle;
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

    public String getMovie_title() {
        return movieTitle;
    }

    public void setMovie_id(String movie_title) {
        this.movieTitle = movie_title;
    }
}
