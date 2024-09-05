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
    private int id_bran;
    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;
    public Branches(){
    }
    public Branches(int id, String neighborhood) {
        this.id_bran = id;
        this.neighborhood = neighborhood;
    }
    public int getId_bran() {
        return id_bran;
    }

    public void setId_bran(int id_bran) {
        this.id_bran = id_bran;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }
}
