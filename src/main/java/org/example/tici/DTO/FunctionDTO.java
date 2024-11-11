package org.example.tici.DTO;

import java.time.LocalTime;

public class FunctionDTO {

    private int idfun;
    private int dayMonth;
    private LocalTime startTime;
    private LocalTime endTime;
    private int branchId;
    private String movieTitle;
    private int projectionRoomNumber;

    public int getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(int dayMonth) {
        this.dayMonth = dayMonth;
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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public int getProjectionRoomNumber() {
        return projectionRoomNumber;
    }

    public void setProjectionRoomNumber(int projectionRoomNumber) {
        this.projectionRoomNumber = projectionRoomNumber;
    }

    public int getIdfun() {
        return idfun;
    }

    public void setIdfun(int idfun) {
        this.idfun = idfun;
    }
}
