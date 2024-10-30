package org.example.tici.Model.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;@Entity
@Table(name = "billboard")
public class Billboard {

    @Id
    @Column(name = "id", nullable = false)
    private int idBill;

    @ManyToOne
    @JoinColumn(name = "id_branch", nullable = false)
    private Branches branchId;


    @ManyToMany(mappedBy = "billboards")
    private List<Movie> movies;

    public Billboard(int idBill, Branches branchId) {
        this.idBill = idBill;
        this.branchId = branchId;
        this.movies = new ArrayList<>();
    }

    public Billboard() {
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public Branches getBranchId() {
        return branchId;
    }

    public void setBranchId(Branches branchId) {
        this.branchId = branchId;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

}
