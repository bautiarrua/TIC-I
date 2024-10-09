package org.example.tici.Repository;
import org.example.tici.Model.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findByTitle(String title);

}

