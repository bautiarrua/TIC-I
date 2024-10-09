package org.example.tici.Repository;

import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillboardRepository extends JpaRepository<Billboard, Integer> {

    Billboard findByIdBill(int idBill);
    Billboard findByBranchId_IdBran(int idBran);
}
