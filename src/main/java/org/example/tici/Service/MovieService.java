package org.example.tici.Service;

import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public Movie addMovie(Movie movie)  throws YaExiste {
        if(movieRepository.findByTitle(movie.getTitle()) != null){
            throw new YaExiste();
        }
        movieRepository.save(movie);
        return movie;
    }
}
