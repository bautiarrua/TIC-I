package org.example.tici.Repository;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Integer> {

    Branches findByIdBran(int idBran);

    Optional<Branches> findBranchesByIdBran(int idBran);

}