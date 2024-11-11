package org.example.tici.Repository;
import org.example.tici.Model.Entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Integer> {
    Function findByIdFun(int idFun);

    @Query("SELECT f FROM Function f WHERE f.branchId.idBran = :branchId AND f.movie.title = :movieTitle AND f.dayMonth = :dayMonth")
    List<Function> findByBranchMovieAndDate(@Param("branchId") int branchId,
                                            @Param("movieTitle") String movieTitle,
                                            @Param("dayMonth") int dayMonth);

}