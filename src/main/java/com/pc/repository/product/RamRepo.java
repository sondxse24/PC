package com.pc.repository.product;

import com.pc.model.product.Ram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepo extends JpaRepository<Ram,Integer> {
}
