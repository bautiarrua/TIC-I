package org.example.tici.Model.Entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

@Entity
@Table(name = "billboard")
public class Billboard {

    @Id
    @Column(name = "id", nullable = false)
    private int Id_Bill;

    @ManyToOne
    @JoinColumn(name = "id_branch", nullable = false)
    private Branches branchId;

    @ElementCollection
    private List<Integer> movies;

    public Billboard(int id_Bill, Branches branchId, List<Integer> movies) {
        this.Id_Bill = id_Bill;
        this.branchId = branchId;
        this.movies = movies;
    }

    public Billboard() {

    }

    public int getId_Bill() {
        return Id_Bill;
    }

    public void setId_Bill(int id_Bill) {
        Id_Bill = id_Bill;
    }

    public Branches getBranchId() {
        return branchId;
    }

    public void setBranchId(Branches branchId) {
        this.branchId = branchId;
    }

    public List<Integer> getMovie() {
        return movies;
    }

    public void setMovie(List<Integer> movies) {
        this.movies = movies;
    }
}
