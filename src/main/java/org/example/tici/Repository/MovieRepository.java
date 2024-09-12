package org.example.tici.Repository;

import org.example.tici.Model.Entities.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository {
    Movie findByIdMovie(int Id);
}
