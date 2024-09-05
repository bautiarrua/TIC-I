package org.example.tici.Model.Entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalTime;
import java.util.Date;
@Entity
@Table(name = "function")
public class Function {

    @Id
    @Column(name = "id_function")
    private int id_fun;

    @Column(name = "day_month", nullable = false)
    private Date dayMonth;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "projection_room_number", nullable = false)
    private int projectionRoomNumber;

    @Column(name = "movie_title", nullable = false, length = 200)
    private String movieTitle;

    // Si deseas mapear las relaciones con otras entidades
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projection_room_number", insertable = false, updatable = false)
    private ProjectionRoom projectionRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title", insertable = false, updatable = false)
    private Movie movie;
    public Function(){

    }
    public Function(int id_fun,Date date,LocalTime start_time,
                    LocalTime end_time, int projection_room_number, String movie_title ){
        this.id_fun=id_fun;
        this.dayMonth=date;
        this.startTime = start_time;
        this.endTime = end_time;
        this.projectionRoomNumber = projection_room_number;
        this.movieTitle = movie_title;
    }

    public int getId_fun() {
        return id_fun;
    }

    public void setId_fun(int id_fun) {
        this.id_fun = id_fun;
    }

    public Date getDate() {
        return dayMonth;
    }

    public void setDate(Date date) {
        this.dayMonth = date;
    }

    public LocalTime getStart_time() {
        return startTime;
    }

    public void setStart_time(LocalTime start_time) {
        this.startTime = start_time;
    }

    public LocalTime getEnd_time() {
        return endTime;
    }

    public void setEnd_time(LocalTime end_time) {
        this.endTime = end_time;
    }

    public int getProjection_room_number() {
        return projectionRoomNumber;
    }

    public void setProjection_room_number(int projection_room_number) {
        this.projectionRoomNumber = projection_room_number;
    }

    public String getMovie_title() {
        return movieTitle;
    }

    public void setMovie_id(String movie_title) {
        this.movieTitle = movie_title;
    }
}
