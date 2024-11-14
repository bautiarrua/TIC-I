package org.example.tici.DTO;

import java.util.List;

public class BookingRequest {
    private int functionId;
    private List<Integer> seatsNumberToReserve;

    public BookingRequest() {
    }

    public BookingRequest(int functionId, List<Integer> seatsNumberToReserve) {
        this.functionId = functionId;
        this.seatsNumberToReserve = seatsNumberToReserve;
    }


    public List<Integer> getSeatsNumberToReserve() {
        return seatsNumberToReserve;
    }


    public void setSeatsNumberToReserve(List<Integer> seatsNumberToReserve) {
        this.seatsNumberToReserve = seatsNumberToReserve;
    }


    public int getFunctionId() {
        return functionId;
    }


    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }
}
