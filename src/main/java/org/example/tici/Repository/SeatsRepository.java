package org.example.tici.Repository;

import org.example.tici.Model.Entities.Function;
import org.example.tici.Model.Entities.SeatBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatsRepository extends JpaRepository<SeatBook, Integer> {
    List<SeatBook> findByFunctionAndSeatNumberIn(Function function, List<Integer> seatNumbers);
}
