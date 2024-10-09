package org.example.tici.Service;
import org.example.tici.Exceptions.LimiteAlcanzado;
import org.example.tici.Exceptions.NoExiste;
import org.example.tici.Exceptions.YaExiste;
import org.example.tici.Model.Entities.Billboard;
import org.example.tici.Model.Entities.Branches;
import org.example.tici.Repository.BillboardRepository;
import org.example.tici.Repository.BranchesRepository;
import org.example.tici.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BillboardService {
    @Autowired
    private BillboardRepository billboardRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BranchesRepository branchesRepository;

    public Billboard addBillboard(Billboard billboard) throws  YaExiste, NoExiste{
        if(billboardRepository. findByIdBill(billboard.getIdBill()) != null){
            throw new YaExiste();
        }
        if(branchesRepository.findByIdBran(billboard.getBranchId().getIdBran()) == null){
            throw new NoExiste();
        }
        billboardRepository.save(billboard);
        return billboard;
    }
    public Billboard addMovieToBillboard(String movieTitle, int idBill) throws NoExiste, YaExiste {
        if(movieRepository.findByTitle(movieTitle).getTitle() == null){
            throw new NoExiste();
        }
        if(billboardRepository.findByIdBill(idBill) == null){
            throw new NoExiste();
        }
        Billboard billboard = billboardRepository.findByIdBill(idBill);
        if(billboard.getMovie().contains(movieTitle)){
            System.out.println("Esta cancion ya esta agregada");
            throw new YaExiste();
        }
        billboard.getMovie().add(movieTitle);
        return billboardRepository.save(billboard);
    }
    public Billboard getBillboardByBranchId(int branchId) throws NoExiste {
        if(billboardRepository.findByIdBill(branchId) == null){
            throw new NoExiste();
        }
        Billboard billboard = billboardRepository.findByBranchId_IdBran(branchId);
        if (billboard == null) {
            throw new NoExiste();
        }
        return billboard;

    }




}
