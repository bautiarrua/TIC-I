package org.example.tici.Model.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "billboard")
public class Billboard {

    @Id
    @Column(name = "id", nullable = false)
    private int idBill;

    @ManyToOne
    @JoinColumn(name = "id_branch", nullable = false)
    private Branches branchId;

    @ElementCollection
    private List<String> movies;

    public Billboard(int idBill, Branches branchId, List<String> movies) {
        this.idBill = idBill;
        this.branchId = branchId;
        this.movies = movies;
    }

    public Billboard() {

    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        idBill = idBill;
    }

    public Branches getBranchId() {
        return branchId;
    }

    public void setBranchId(Branches branchId) {
        this.branchId = branchId;
    }

    public List<String> getMovie() {
        return movies;
    }

}
