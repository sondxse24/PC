package com.laptop.repository.product;

import com.laptop.model.product.Vga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VgaRepo extends JpaRepository<Vga,Integer> {
}
