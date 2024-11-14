package org.example.tici.Repository;

import org.example.tici.Model.Entities.SeatBook;
import org.example.tici.Model.Entities.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SeatBookRepository extends JpaRepository<SeatBook, Long> {

    @Query("SELECT s.seatNumber FROM SeatBook s WHERE s.function = :function")
    List<Integer> findReservedSeatNumbersByFunction(@Param("function") Function function);

}