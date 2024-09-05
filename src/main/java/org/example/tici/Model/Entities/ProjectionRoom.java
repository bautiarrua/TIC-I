package org.example.tici.Model.Entities;

import jakarta.persistence.*;

@Entity
public class ProjectionRoom {
    @Id
    private int roomNumber;

    @Column(nullable = false, length = 50) // indica que no pueden haber valores null y un largo maximo de 50 caracteres
    private String type;

    @Column (nullable = false)
    private int numSeats;

    @Column (nullable = false)
    private int numRows;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branches branch;

    public ProjectionRoom() {
    }

    public ProjectionRoom(int roomNumber, String type, int numSeats, int numRows, Branches branch) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.numSeats = numSeats;
        this.numRows = numRows;
        this.branch = branch;
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
        return branch;
    }

    public void setBranch(Branches branch) {
        this.branch = branch;
    }
}
