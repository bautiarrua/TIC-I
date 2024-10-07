package org.example.tici.Repository;

import org.example.tici.Model.Entities.Movie;
import org.example.tici.Model.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String title);

}

