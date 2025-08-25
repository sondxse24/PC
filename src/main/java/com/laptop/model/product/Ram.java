package com.laptop.model.product;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ram_category")
public class Ram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "capacity", nullable = false)
    private String capacity;

    @Column(name = "bus_speed", nullable = false)
    private String busSpeed;

    public Ram(String name, String capacity, String busSpeed) {
        this.name = name;
        this.capacity = capacity;
        this.busSpeed = busSpeed;
    }
}
