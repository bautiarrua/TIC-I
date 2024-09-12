package org.example.tici.Repository;

import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.springframework.stereotype.Repository;

@Repository
public interface BillboardRepository {
    Billboard findById_bran(int Id);
}
