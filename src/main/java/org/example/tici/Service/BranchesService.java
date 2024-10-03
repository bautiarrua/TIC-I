package org.example.tici.Service;

import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Repository.BillboardRepository;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BranchesService {
    @Autowired
    private BranchesRepository branchesRepository;
    private BillboardRepository billboardRepository;
    private MovieRepository movieRepository;

    public List<Movie> consultBillboard(Branches branch) throws NoExiste {
        List<Movie> movies = new ArrayList<>();
        if (branchesRepository.findById(branch.getIdBran()) == null) {
            throw new NoExiste();
        }
        Billboard bill = billboardRepository.findByIdBran(branch.getIdBran());
        for (int i = 0; i < bill.getMovie().size(); i++) {
            int id_M = bill.getMovie().get(i);
            movies.add(movieRepository.findByIdMovie(id_M));
        }
        return movies;

    }

}
