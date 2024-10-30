package org.example.tici.Repository;
import org.example.tici.Model.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findByTitle(String title);

    @Query("SELECT m FROM Movie m " +
            "JOIN m.billboards b " +
            "WHERE (:branchId IS NULL OR b.branchId = :branchId) " +
            "AND (:category IS NULL OR m.genre = :category) " +
            "AND (:language IS NULL OR m.language = :language) " +
            "AND (:type IS NULL OR m.type = :type)")
    List<Movie> findFilteredMovies(@Param("branchId") Integer branchId,
                                   @Param("category") String category,
                                   @Param("language") String language,
                                   @Param("type") String type);

}

