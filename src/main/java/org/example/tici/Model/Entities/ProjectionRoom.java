package org.example.tici.Model.Entities;

import jakarta.persistence.*;

@Entity
public class ProjectionRoom {
    @Id
    private int roomNumber;

    @Column(nullable = false, length = 50)
    private String type;

    @Column (nullable = false)
    private int numSeats;

    @Column (nullable = false)
    private int numRows;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branches branchId;

    public ProjectionRoom() {
    }

    public ProjectionRoom(int roomNumber, String type, int numSeats, int numRows, Branches branchId) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.numSeats = numSeats;
        this.numRows = numRows;
        this.branchId = branchId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumSeats() {
        return numSeats;
    }

    public void setNumSeats(int numSeats) {
        this.numSeats = numSeats;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public Branches getBranch() {
        return branchId;
    }

    public void setBranch(Branches branchId) {
        this.branchId = branchId;
    }
}
