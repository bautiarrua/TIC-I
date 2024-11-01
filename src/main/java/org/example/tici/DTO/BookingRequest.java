package org.example.tici.DTO;

import java.util.List;

public class BookingRequest {
    private int functionId; // ID de la función
    private List<Integer> seatsNumberToReserve; // Lista de asientos a reservar

    // Constructor vacío
    public BookingRequest() {
    }

    // Constructor que inicializa el ID de la función y los asientos a reservar
    public BookingRequest(int functionId, List<Integer> seatsNumberToReserve) {
        this.functionId = functionId; // Usar functionId para mayor claridad
        this.seatsNumberToReserve = seatsNumberToReserve;
    }

    // Getter para obtener la lista de asientos a reservar
    public List<Integer> getSeatsNumberToReserve() {
        return seatsNumberToReserve;
    }

    // Setter para establecer la lista de asientos a reservar
    public void setSeatsNumberToReserve(List<Integer> seatsNumberToReserve) {
        this.seatsNumberToReserve = seatsNumberToReserve;
    }

    // Getter para obtener el ID de la función
    public int getFunctionId() {
        return functionId;
    }

    // Setter para establecer el ID de la función
    public void setFunctionId(int functionId) {
        this.functionId = functionId;
    }
}
