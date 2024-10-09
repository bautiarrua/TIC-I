package org.example.tici.Model.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branches")
public class Branches {
    @Id
    @Column(name = "id", nullable = false)
    private int idBran;

    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @Column(name = "nro_rooms", nullable = false)
    private int nroRooms;

    private int cantidad;
    public Branches(){
    }
    public Branches(int id, String neighborhood, int nroRooms) {
        this.idBran = id;
        this.neighborhood = neighborhood;
        this.nroRooms = nroRooms;
        this.cantidad = 0;
    }
    public int getIdBran() {
        return idBran;
    }

    public void setIdBran(int idBran) {
        this.idBran = idBran;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public int getNroRooms() {
        return nroRooms;
    }

    public void setNroRooms(int nroRooms) {
        this.nroRooms = nroRooms;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
