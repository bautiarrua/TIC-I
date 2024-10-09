package org.example.tici.Service;

import org.example.tici.Exceptions.LimiteAlcanzado;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Model.Entities.ProjectionRoom;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Repository.ProjectionRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectionRoomService {

    @Autowired
    private ProjectionRoomRepository projectionRoomRepository;

    @Autowired
    private BranchesRepository branchesRepository;

    public ProjectionRoom addRoom(ProjectionRoom room) throws YaExiste, NoExiste, LimiteAlcanzado {
        if(projectionRoomRepository.findByRoomNumber(room.getRoomNumber()) != null){
            throw new YaExiste();
        }
        if(branchesRepository.findByIdBran(room.getBranch().getIdBran()) == null){
            throw new NoExiste();
        }
        Branches branch = branchesRepository.findByIdBran(room.getBranch().getIdBran());
        int prevC = branch.getCantidad();
        if(prevC == branch.getNroRooms()){
            throw new LimiteAlcanzado();
        }
        branch.setCantidad(prevC + 1);
        projectionRoomRepository.save(room);
        return room;
    }

}
