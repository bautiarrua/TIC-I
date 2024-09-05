package org.example.tici.Model.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "booking")
public class Booking {
    @Id
    private int bookingId;




}
