package com.pc.model.product;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ram_category")
public class Vga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name",  nullable = false, unique = true)
    private String name;

    @Column(name = "capacity", nullable = false)
    private String capacity;
}
