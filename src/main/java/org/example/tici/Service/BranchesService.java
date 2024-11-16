package org.example.tici.Service;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Repository.BranchesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BranchesService {
    @Autowired
    private BranchesRepository branchesRepository;

    public Branches addBran(Branches branch) throws YaExiste{
        if (branch == null) {
            throw new IllegalArgumentException("Branch cannot be null");
        }

        if(branchesRepository.findByIdBran(branch.getIdBran()) != null){
            throw new YaExiste();
        }
        branchesRepository.save(branch);
        return branch;
    }

}
