package com.laptop.repository.product;

import com.laptop.model.product.Mainboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainboardRepo extends JpaRepository<Mainboard,Integer> {
}
