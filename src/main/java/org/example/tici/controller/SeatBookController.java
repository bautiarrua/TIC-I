package org.example.tici.controller;

import org.example.tici.Model.Entities.Function;
import org.example.tici.Repository.FunctionRepository;
import org.example.tici.Service.SeatBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seats")
public class SeatBookController {

    private final SeatBookService seatBookService;
    private final FunctionRepository functionRepository;

    @Autowired
    public SeatBookController(SeatBookService seatBookService, FunctionRepository functionRepository) {
        this.seatBookService = seatBookService;
        this.functionRepository = functionRepository;
    }

    @GetMapping("/reserved/fun/{functionId}")
    public List<Integer> getReservedSeats(@PathVariable("functionId") int functionId) {
        Function function = functionRepository.findByIdFun(functionId);
        if (function == null) {
            throw new RuntimeException("Función no encontrada");
        }
        return seatBookService.getReservedSeatNumbersByFunction(function);
    }
}