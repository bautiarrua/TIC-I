package org.example.tici.Repository;

import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchesRepository extends JpaRepository<Branches, Integer> {

    Branches findByIdBran(int idBran);

}