package com.pc.model.product;

import com.pc.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @Column(name = "image", nullable = false, unique = true)
    private String image;

    @ManyToOne
    @JoinColumn(name = "CPU", nullable = false)
    private Cpu cpu;

    @ManyToOne
    @JoinColumn(name = "mainboard", nullable = false)
    private Mainboard mainboard;

    @ManyToOne
    @JoinColumn(name = "RAM", nullable = false)
    private Ram ram;

    @ManyToOne
    @JoinColumn(name = "VGA", nullable = false)
    private Vga vga;

    @Column(name = "storage", nullable = false)
    private String storage;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "guarantee", nullable = false)
    private String guarantee;

    @Column(name = "promotion", nullable = false)
    private String promotion;

    @Column(name = "status", nullable = false)
    private ProductStatus status;

    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    public Product(String name, Double price, Category category, String image, Cpu cpu, Mainboard mainboard, Ram ram, Vga vga, String storage, String size, Double weight, String guarantee, String promotion, ProductStatus status, String manufacturer) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.image = image;
        this.cpu = cpu;
        this.mainboard = mainboard;
        this.ram = ram;
        this.vga = vga;
        this.storage = storage;
        this.size = size;
        this.weight = weight;
        this.guarantee = guarantee;
        this.promotion = promotion;
        this.status = status;
        this.manufacturer = manufacturer;
    }
}
