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
    public Branches(){
    }
    public Branches(int id, String neighborhood) {
        this.idBran = id;
        this.neighborhood = neighborhood;
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
}
