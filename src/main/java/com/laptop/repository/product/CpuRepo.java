package com.laptop.repository.product;

import com.laptop.model.product.Cpu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CpuRepo extends JpaRepository<Cpu,Integer> {
}
