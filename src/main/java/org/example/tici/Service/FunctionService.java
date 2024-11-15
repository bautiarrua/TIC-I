package org.example.tici.Service;
import org.example.tici.DTO.FunctionDTO;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.Function;
import org.example.tici.Model.Entities.Movie;
import org.example.tici.Model.Entities.ProjectionRoom;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Repository.MovieRepository;
import org.example.tici.Repository.ProjectionRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FunctionService {


    @Autowired
    FunctionRepository functionRepository;

    @Autowired
    BranchesRepository branchesRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ProjectionRoomRepository projectionRoomRepository;


    public List<Function> getFunctionsByBranchMovieAndDate(int branchId, String movieTitle, int dayMonth) {
        return functionRepository.findByBranchMovieAndDate(branchId, movieTitle, dayMonth);
    }

    public Function addFunction(FunctionDTO functionDTO) throws NoExiste {
         if(movieRepository.findByTitle(functionDTO.getMovieTitle()) == null|| functionDTO.getMovieTitle().isEmpty()) {
             System.out.println("NO se encontro la pelicula");
             throw new NoExiste();
         }
         System.out.println("Encontro la pelicula");
         Movie movie = movieRepository.findByTitle(functionDTO.getMovieTitle());

         if(projectionRoomRepository.findByRoomNumber(functionDTO.getProjectionRoomNumber()) == null){
             System.out.println("NO encontro el room");
             throw new NoExiste();
         }
         System.out.println("Encontro el Room");

        ProjectionRoom projectionRoom = projectionRoomRepository.findByRoomNumber(functionDTO.getProjectionRoomNumber());
        System.out.println("numero del room " + projectionRoom.getRoomNumber());
        if(branchesRepository.findByIdBran(functionDTO.getBranchId()) == null){
            System.out.println("NO se encontro la branch");
            throw new NoExiste();
        }
        System.out.println("Encontro la branch");

        Branches branches = branchesRepository.findByIdBran(functionDTO.getBranchId());
        Function function = new Function(functionDTO.getIdfun(), functionDTO.getDayMonth(),
                functionDTO.getStartTime(), functionDTO.getEndTime(), projectionRoom, movie,
               branches);

        return functionRepository.save(function);
    }

}
