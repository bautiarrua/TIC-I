package org.example.tici.Service;

import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Exceptions.YaExiste;
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

    public Branches addBran(Branches branch) throws YaExiste{
        if(branchesRepository.findByIdBran(branch.getIdBran()) != null){
            throw new YaExiste();
        }
        branchesRepository.save(branch);
        return branch;
    }

}
