package org.example.tici.Model.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "billboard")
public class Billboard {

    @Id
    @Column(name = "id_bill", nullable = false)
    private int idBill;

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branches branchId;


    @ManyToMany
    @JoinTable(
            name = "billboard_movies",
            joinColumns = @JoinColumn(name = "billboard_id"),
            inverseJoinColumns = @JoinColumn(name = "id_movie")
    )
    private List<Movie> movies;


    public Billboard(int idBill, Branches branchId) {
        this.idBill = idBill;
        this.branchId = branchId;
        this.movies = new ArrayList<>();
    }

    public Billboard() {
        this.movies = new ArrayList<>();
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
