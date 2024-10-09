package org.example.tici.Repository;
import org.example.tici.Model.Entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Integer> {
    Function findByIdFun(int idFun);
}
