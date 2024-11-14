package org.example.tici.Service;

import org.example.tici.Model.Entities.Function;
import org.example.tici.Repository.SeatBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatBookService {

    private final SeatBookRepository seatBookRepository;

    @Autowired
    public SeatBookService(SeatBookRepository seatBookRepository) {
        this.seatBookRepository = seatBookRepository;
    }

    public List<Integer> getReservedSeatNumbersByFunction(Function function) {
        return seatBookRepository.findReservedSeatNumbersByFunction(function);
    }
}