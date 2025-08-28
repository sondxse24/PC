package com.pc.model.product;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mainboard_category")
public class Mainboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    public Mainboard(String name, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
    }
}
